package com.tingshuo.asset.fallback;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

/**
 * packageName com.tingshuo.asset.fallback
 *
 * @author tingshuo
 * @version JDK 8
 * @className SentinelFeignErrorDecoder (此处以class为例)
 * @date 2025/12/5-23:06
 * @description TODO
 */
@Component
public class SentinelFeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        // 如果是 5xx 服务器错误，转为 RuntimeException
        if (response.status() >= 500) {
            return new RuntimeException("Remote service [" + response.request().url() + "] returned " + response.status());
        }
        // 其他错误（如 4xx）使用默认处理
        return new Default().decode(methodKey, response);
    }
}
