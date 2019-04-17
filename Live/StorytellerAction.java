package Lab.Live;

import Lab.Exceptions.IsNakedException;

import java.io.IOException;

public interface StorytellerAction {
    void tellStory() throws IsNakedException, IOException;
    void setLine();
}
