package com.shop.ordersercice.service.impl;

import com.shop.ordersercice.dao.OrderDAO;
import com.shop.ordersercice.dao.impl.OrderDAOImpl;
import com.shop.bucketservice.entity.Bucket;
import com.shop.ordersercice.entity.Order;
import com.shop.ordersercice.service.OrderService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    OrderDAO orderDAO = new OrderDAOImpl();
    Map<Integer, List<Integer>> orders = new HashMap<>();

    @Override
    public boolean makeOrder(Order order) {
        return orderDAO.makeOrder(order);
    }

    @Override
    public boolean confirmeOrder(int id) {
        return orderDAO.confirmeOrder(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @KafkaListener(topics = "makeOrder", groupId = "orders")
    public void makeOrder(Bucket bucket) {
        List<Integer> productList = orders.get(bucket.getBucketId());
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Integer productId: productList) {
            sum = sum.add(getProductPrice(productId));
        }
        Order order = new Order(bucket.getBucketId(), sum);
        makeOrder(order);

        clearCommonBucket(bucket);
    }

    @KafkaListener(topics = "addProduct", groupId = "orders")
    public void addProductToCommonBucket(Bucket bucket) {
        Integer userId = bucket.getBucketId();
        Integer productId = bucket.getProductId();;
        List<Integer> userProducts = orders.get(userId);
        if(orders.containsKey(userId)){
            userProducts.add(productId);
            orders.put(userId, userProducts);
        } else {
            List<Integer> newUserProducts = new ArrayList<>();
            newUserProducts.add(productId);
            orders.put(userId, newUserProducts);
        }
        System.out.println(orders);
    }

    @KafkaListener(topics = "delete", groupId = "orders")
    public void deleteProductFromCommonBucket(Bucket bucket) {
        Integer userId = bucket.getBucketId();
        Integer productId = bucket.getProductId();
        List<Integer> userProducts = orders.get(userId);
        if(orders.containsKey(userId) && userProducts.contains(productId)){
            userProducts.remove(productId);
            orders.put(userId, userProducts);
        }
        System.out.println(orders);
    }

    @KafkaListener(topics = "clear", groupId = "orders")
    public void clearCommonBucket(Bucket bucket) {
        int userId = bucket.getBucketId();
        if(orders.containsKey(userId)){
            orders.remove(userId);
        }
        System.out.println(orders);
    }


    private BigDecimal getProductPrice(int productId) {
        BigDecimal sum;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpUriRequest httpGet = RequestBuilder.get()
                    .setUri(new URI("http://localhost:8082/products/get/price"))
                    .addParameter("productId", String.valueOf(productId))
                    .build();

            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                sum = BigDecimal.valueOf(Double.parseDouble(EntityUtils.toString(response.getEntity())));
            } finally {
                response.close();
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sum;
    }

}
