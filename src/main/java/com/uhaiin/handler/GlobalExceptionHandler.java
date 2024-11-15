package com.uhaiin.handler;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.uhaiin.web.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * 处理系统异常的异常处理器。 当系统发生异常时，此方法将捕获异常并返回一个包含异常信息的消息对象。
	 *
	 * @param e 异常对象，表示发生的系统异常。
	 * @return Message<String> 返回一个包含错误信息的消息对象。
	 */
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ApiResponse<String> handlerException(Exception e) {
		log.error("Exception ==> {}", e.getMessage(), e);
		return ApiResponse.failure(e.getMessage());
	}

	/**
	 * 全局异常处理器，用于捕获运行时异常并返回标准化的错误响应 该处理器主要用来处理未被其他特定异常处理器捕获的运行时异常
	 * 将异常信息记录在日志中，并返回一个包含错误详情的ResultData对象
	 *
	 * @param e 运行时异常对象，包含了错误信息
	 * @return 包含错误码和错误信息的ResultData对象
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiResponse<String> exception(Exception e) {
		log.error("RuntimeException ==> {}", e.getMessage(), e);
		return ApiResponse.failure(e.getMessage());
	}

	/**
	 * 处理方法参数不合法的异常。
	 * 当方法参数验证失败时，会抛出MethodArgumentNotValidException异常，此异常处理器将捕获该异常并返回一个错误消息。
	 *
	 * @param e MethodArgumentNotValidException，表示方法参数验证异常。
	 * @return 返回一个包含错误消息的对象。
	 */
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiResponse<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("MethodArgumentNotValidException ==> {}", e.getMessage(), e);
		return ApiResponse.failure(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
	}

}