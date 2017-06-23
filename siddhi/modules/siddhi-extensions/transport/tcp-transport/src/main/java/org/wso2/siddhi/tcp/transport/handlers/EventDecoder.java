/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied. See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.siddhi.tcp.transport.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.wso2.siddhi.tcp.transport.converter.SiddhiEventConverter;
import org.wso2.siddhi.tcp.transport.utils.StreamTypeHolder;

import java.util.List;


public class EventDecoder extends ByteToMessageDecoder {
    private StreamTypeHolder streamInfoHolder;

    public EventDecoder(StreamTypeHolder streamInfoHolder) {
        this.streamInfoHolder = streamInfoHolder;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < 5) {
            return;
        }
        SiddhiEventConverter.toConvertToSiddhiEvents(in, streamInfoHolder);
    }
}
