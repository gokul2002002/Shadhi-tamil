package com.sayit.shadhi.Interfaces;

import com.sayit.shadhi.Enums.AstrologyStatus;
import com.sayit.shadhi.Enums.GeneralStatus;
import com.sayit.shadhi.Models.AccesRequest;

import java.util.List;

public interface RequestInterface {
    public List<AccesRequest> getAllRequestOfUser();
    public AstrologyStatus getStatusOFaPair(long chartId);
    public GeneralStatus giveRequestToPair(long pairId);

}
