package com.sayit.shadhi.Services;

import com.sayit.shadhi.Enums.AstrologyStatus;
import com.sayit.shadhi.Enums.GeneralStatus;
import com.sayit.shadhi.Interfaces.RequestInterface;
import com.sayit.shadhi.Models.AccesRequest;
import com.sayit.shadhi.Repositories.AccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestsService implements RequestInterface {

    private final AccessRepository accessRepository;

    public void getRequest(){

    }
    @Override
    public List<AccesRequest> getAllRequestOfUser() {
        return List.of();
    }

    @Override
    public AstrologyStatus getStatusOFaPair(long chartId) {
        return null;
    }

    @Override
    public GeneralStatus giveRequestToPair(long pairId) {
        return GeneralStatus.SENDED;
    }


}
