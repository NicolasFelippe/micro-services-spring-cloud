package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.entities.Worker;
import com.devsuperior.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final WorkerFeignClient workerFeignClient;

    public PaymentService(WorkerFeignClient workerFeignClient) {
        this.workerFeignClient = workerFeignClient;
    }

    public Payment getPayment(long workerId, int days) {
        Worker worker = workerFeignClient.findById(workerId).getBody();

        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
