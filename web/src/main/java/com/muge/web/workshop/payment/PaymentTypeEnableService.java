package com.muge.web.workshop.payment;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ll * @Date 2018/1/10 23:54
 */
@Service
public class PaymentTypeEnableService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentTypeEnableService.class);
    /**
     * 默认认为超时时间（可动态配置） 2S
     */
    private static final int TASK_INTERRUPT_TIME = 2000;
    /**
     * 默认最大线程数量 * <p> * ((线程等待时间+线程CPU时间 ) / 线程CPU时间 ) * CPU数目
     */
    private static final int DEFAULT_MAX_THREAD = ((1 + 2) * 2) * Runtime.getRuntime().availableProcessors();
    private static final ExecutorService executor = new ThreadPoolExecutor(DEFAULT_MAX_THREAD, DEFAULT_MAX_THREAD, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024), new ThreadFactoryBuilder().setDaemon(true).setNameFormat("RealTime PaymentTypeEnableFilter-%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());
    @Autowired
    private PaymentRemoteService paymentRemoteService;

    //PaymentRemoteSerivce
    public PaymentTypeEnableService(PaymentRemoteService paymentRemoteService) {
        this.paymentRemoteService = paymentRemoteService;
    }

    /**
     * 获取可用支付方式列表 * * @param allPaymentList 支付方式列表 * @return
     */
    public List<String> acquireEnablePayment(List<String> allPaymentList) {
        List<String> paymentTypeEnableList = new ArrayList<String>();
        if (CollectionUtils.isEmpty(allPaymentList)) {
            return paymentTypeEnableList;
        }
        CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
        for (final String paymentType : allPaymentList) {
            completionService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    ConsultResult consultResult = paymentRemoteService.isEnabled(paymentType);
                    if (consultResult != null && consultResult.getIsEnable()) {
                        return paymentType;
                    }
                    logger.error("PaymentRemoteService isEnabled acquire failure, paymentType:{} | {}", paymentType, consultResult);
                    return null;
                }
            });
        }
        for (int i = 0; i < allPaymentList.size(); i++) {
            try {
                String paymentType = completionService.poll(TASK_INTERRUPT_TIME, TimeUnit.MILLISECONDS).get();
                if (!StringUtils.isEmpty(paymentType)) {
                    paymentTypeEnableList.add(paymentType);
                }
            } catch (InterruptedException | ExecutionException e) {
                logger.error("error occurs while acquireEnablePayment special re by batch, ThreadName : {}", Thread.currentThread().getName(), e.getMessage(), e);
            } finally {
                if (executor != null) {
                    executor.shutdown();
                }
            }
        }
        return paymentTypeEnableList;
    }
}