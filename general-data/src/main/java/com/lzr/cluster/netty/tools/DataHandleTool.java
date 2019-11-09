package com.lzr.cluster.netty.tools;

import com.google.protobuf.ByteString;
import com.google.protobuf.Message;

/**
 * @author lzr
 * @date 2019/6/25 0025 09:36
 */
public final class DataHandleTool {

    /**
     * 将消息处理成byte
     */
    public static ByteString getByteString(Message message){
        ByteString bytes = message.toByteString();
        return bytes;
    }

    /**
     * 发送消息时，根据消息获取CodeType
     */
    public static long getCodeType(Message message){
        return 0;
    }

}
