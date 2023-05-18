package util.gpt;

import com.google.common.base.Joiner;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.image.CreateImageEditRequest;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.image.Image;
import com.theokanning.openai.image.ImageResult;
import com.theokanning.openai.service.OpenAiService;
import io.reactivex.Flowable;

import java.io.File;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

/**
 * GPT test
 *
 * @author jarvansong
 * @Date 2023/4/7 17:17
 */
public class GPT {
    public static void main(String[] args) {
        OpenAiService service = new OpenAiService("",
                Duration.ofSeconds(100));
//        List<ChatMessage> messages = new LinkedList<>();
//        ChatMessage message = new ChatMessage();
//        message.setRole("user");
//        message.setContent("呦吼");
//        messages.add(message);
//        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
//                .messages(messages)
//                .model("gpt-3.5-turbo")
//                .temperature(0.0)
//                .n(1)
//                .stream(true)
//                .build();

//        Flowable<ChatCompletionChunk> flowable = service.streamChatCompletion(chatCompletionRequest);
//        flowable.blockingForEach(x->{
//            if(x.getChoices().get(0).getMessage().getContent() != null){
//                System.out.print(x.getChoices().get(0).getMessage().getContent());
//            }
//        });
//        service.shutdownExecutor();

        //create IMAGE
        long t1 = System.currentTimeMillis();
        CreateImageRequest imageRequest = CreateImageRequest.builder()
                .prompt("A cute baby sea otter").size("512x512").build();
        ImageResult imageResult = service.createImage(imageRequest);
        for (Image image : imageResult.getData()) {
            System.out.println(image.getUrl());
        }

        //edit IMAGE
//        CreateImageEditRequest imageEditRequest = CreateImageEditRequest.builder()
//                .prompt("A cute baby sea otter wearing a beret").size("512x512").responseFormat("url").build();
//        ImageResult imageResultEdit = service.createImageEdit(imageEditRequest, "D://800.png", null);
//        for (Image image : imageResultEdit.getData()) {
//            System.out.println(image.getUrl());
//        }
        System.out.println(System.currentTimeMillis() - t1);
    }
}
