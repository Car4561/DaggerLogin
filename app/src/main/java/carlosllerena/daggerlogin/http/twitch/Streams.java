package carlosllerena.daggerlogin.http.twitch;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Streams {

    @SerializedName("data")
    @Expose
    private List<Stream> streams = null;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(List<Stream> stream) {
        this.streams = stream;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
