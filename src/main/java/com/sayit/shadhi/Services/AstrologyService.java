package com.sayit.shadhi.Services;

import com.sayit.shadhi.DTOs.AstrologerPriceFilter;
import com.sayit.shadhi.DTOs.ChartRequestDTO;
import com.sayit.shadhi.DTOs.ChartScoreDTO;
import com.sayit.shadhi.Enums.AstrologyStatus;
import com.sayit.shadhi.Enums.GeneralStatus;
import com.sayit.shadhi.Exceptions.ChartNotFoundException;
import com.sayit.shadhi.Models.Astrologer;
import com.sayit.shadhi.Models.ChartRequest;
import com.sayit.shadhi.Repositories.AstrologerRepository;
import com.sayit.shadhi.Repositories.ChartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AstrologyService {

    private final ChartRepository chartRepository;

    private final AstrologerRepository astrologerRepository;

    @Transactional
    public String getChartdocuments(ChartRequestDTO chartRequestDTO)throws ChartNotFoundException {
        List<InputStream> documents =  new ArrayList<>();
        Optional<ChartRequest> chartRequest =  chartRepository.findById(chartRequestDTO.getChartRequestID());
        if(chartRequest.isPresent()){
            chartRequest.get().setStatus(AstrologyStatus.INPROGRESS);
        }else {
         throw new ChartNotFoundException("Chart not in present for users");
        }
        return "documents";
    }

    private void giveIntemationToUsers(long brideID , long groomID ,  String message){

    }

    @Transactional
    public GeneralStatus setScoreBYAstrologer(ChartScoreDTO chartScoreDTO)throws ChartNotFoundException{
        Optional<ChartRequest> chartRequest = chartRepository.findById(chartScoreDTO.getChartId());
        if (chartRequest.isPresent()){
           ChartRequest chartData = chartRequest.get();
           chartData.setScore(chartScoreDTO.getScore());
           chartData.setStatus(AstrologyStatus.COMPLETED);
           return GeneralStatus.UPDATED;
        }else {
            throw new ChartNotFoundException("Chart not found in this id");
        }
    }

    public List<Astrologer> getAllAstrologer(){
         return astrologerRepository.findAll();
    }

    public List<Astrologer> getAstrologerByRange(AstrologerPriceFilter astrologerPriceFilter){
         return astrologerRepository.getAllAstrologerBetweenTheRange(astrologerPriceFilter.getStartFrom() , astrologerPriceFilter.getEndAt());
    }

    public List<ChartRequest> getChartRequest(long astrologerId)throws ChartNotFoundException{
         List<ChartRequest> chartRequestList = chartRepository.getChartsOfTheAstrologer(astrologerId);
         if (chartRequestList.isEmpty()){
              throw new ChartNotFoundException("chart not available for you");
         }else {
              return chartRequestList;
         }
    }

    @Transactional
    public String setScoretotheCharts(double scorePoints ,  long ChartId){
         Optional<ChartRequest> chartRequest = chartRepository.findById(ChartId);
         if (chartRequest.isPresent()){
               chartRequest.get().setScore(scorePoints);
         }else {
              throw new ChartNotFoundException("Chart not available for you");
         }
         return "Updated successFuly";
    }

}
