package college.mall.controller;

import college.mall.remote.OrderApiService;
import college.mall.service.DrawPosterDto;
import college.mall.service.DrawService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * User: EDY
 * Date: 2024/5/6
 * Time: 11:24
 * Version:V1.0
 */
@RestController
@RequestMapping("mall/sample")
public class SampleController {

    @Autowired
    private OrderApiService orderApiService;

    @Autowired
    private DrawService drawService;

    @GetMapping("hello/order")
    public String hello() throws InterruptedException {
        Thread.sleep(1000);
        return orderApiService.sample("I am a mall");
    }

    @PostMapping("draw/poster")
    public void drawPoster(MultipartFile topFile, MultipartFile smallFile, MultipartFile orCodeFile, HttpServletResponse response, DrawPosterDto drawPosterDto) throws Exception {
        if (topFile.isEmpty() || smallFile.isEmpty() || orCodeFile.isEmpty() || drawPosterDto.getAmount() == null || StringUtils.isEmpty(drawPosterDto.getMerchantName())
                || StringUtils.isEmpty(drawPosterDto.getMerchantContext())) {
            response.sendError(400, "参数错误");
            return;
        }
        drawService.drawPoster(topFile, smallFile, orCodeFile, response, drawPosterDto);
    }
}
