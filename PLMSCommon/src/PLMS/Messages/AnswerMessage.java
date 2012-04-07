package PLMS.Messages;

import PLMS.frames.AbstractMessage;

/**
 * 服务器的确认、否认消息
 */
public class AnswerMessage  extends AbstractMessage {
    public AnswerMessage()
    {
        super();
    }

    @Override
    protected void setDefaultValue() {

        this.getBody().getCtrl().setPRM(0);
    }

}
