package com.scm.smart_contact_manager.helper;

import com.scm.smart_contact_manager.enums.MessageType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Message {
    String content;

    @Builder.Default
    MessageType type = MessageType.blue;
}
