package com.yunfenghui.jf.api.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunfenghui.jf.api.filter.support.Subject;
import com.yunfenghui.jf.api.request.WhiteScoreSendQueryRequest;
import com.yunfenghui.jf.api.request.WhiteScoreSendRequest;
import com.yunfenghui.jf.api.response.ApiResponse;
import com.yunfenghui.jf.api.response.WhiteScoreSendQueryResponse;
import com.yunfenghui.jf.api.response.WhiteScoreSendResponse;
import com.yunfenghui.jf.score.model.WhiteScoreSendRecord;
import com.yunfenghui.jf.score.service.ScoreService;

@Controller
@RequestMapping("/api")
public class ApiController {
	@Resource(name = ScoreService.ID)
	private ScoreService scoreService;

	/**
	 * 发放白积分
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/sendWhiteScore")
	public ApiResponse sendWhiteScore(WhiteScoreSendRequest request) {
		WhiteScoreSendRecord sendRecord = scoreService
				.sendWhiteScore(createSendRecordRequest(request));
		return ApiResponse.success(
				new WhiteScoreSendResponse(sendRecord.getRecordNo(), sendRecord.getOutTradeNo()));
	}

	private com.yunfenghui.jf.score.model.WhiteScoreSendRequest createSendRecordRequest(
			WhiteScoreSendRequest request) {
		com.yunfenghui.jf.score.model.WhiteScoreSendRequest sendRequest = new com.yunfenghui.jf.score.model.WhiteScoreSendRequest();
		sendRequest.setMemberId(request.getMemberId());
		sendRequest.setPartnerId(request.getPartnerId());
		sendRequest.setOutTradeNo(request.getOutTradeNo());
		sendRequest.setSendScores(request.getSendScores());
		sendRequest.setDealType(request.getDealType());
		sendRequest.setNotifyUrl(request.getNotifyUrl());
		return sendRequest;
	}

	/**
	 * 查询白积分发放记录
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/querySendRecord")
	public ApiResponse querySendRecord(WhiteScoreSendQueryRequest request) {
		WhiteScoreSendRecord sendRecord = scoreService.getWhiteScoreSendRecordBy(
				request.getTradeNo(), Subject.get().getPartnerId(), request.getOutTradeNo());
		if (sendRecord != null) {
			WhiteScoreSendQueryResponse content = WhiteScoreSendQueryResponse.newBuilder().build();
			return ApiResponse.success(content);
		}
		return ApiResponse.fail(null);
	}
}
