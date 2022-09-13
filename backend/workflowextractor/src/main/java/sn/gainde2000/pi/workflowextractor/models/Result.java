package sn.gainde2000.pi.workflowextractor.models;

import java.io.Serializable;

public class Result  implements Serializable {

    String name;
    String uri;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }

    public Result (String name, String uri) {
        super();
        this.name = name;
        this.uri = uri;
    }

    public Result () {}


}
