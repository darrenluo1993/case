package pers.darren.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsSchema;

import java.io.File;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JacksonPropCase {

    private static final String PROPERTIES = """
            tick-time=2000
            data-dir=/var/zookeeper
            client-port=2181
            init-limit=5
            sync-limit=2
            server.1=zoo1:2888:3888
            server.2=zoo2:2888:3888
            server.3=zoo3:2888:3888
            unknown=This is an unknown property!
            """;

    public static void main(String[] args) throws Exception {
        JavaPropsMapper mapper = new JavaPropsMapper();
        // properties转java对象
        ZKConfig zkConfig = mapper.readValue(PROPERTIES, ZKConfig.class);
        System.out.println(zkConfig);
        System.out.println();
        // properties转java对象
        Properties properties = new Properties();
        properties.load(new StringReader(PROPERTIES));
        zkConfig = mapper.readPropertiesAs(properties, ZKConfig.class);
        System.out.println(zkConfig);
        System.out.println();
        // java对象转properties
        System.out.println(mapper.writeValueAsString(zkConfig));
        // 读取系统属性转map
        System.out.println(mapper.readSystemPropertiesAs(new JavaPropsSchema(), Map.class));
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class ZKConfig {
    @JsonProperty("tick-time")
    public int tickTime;
    @JsonProperty("data-dir")
    public File dataDir;
    @JsonProperty("client-port")
    public int clientPort;
    @JsonProperty("init-limit")
    public int initLimit;
    @JsonProperty("sync-limit")
    public int syncLimit;
    @JsonProperty("server")
    public List<ZKServer> servers;

    @Override
    public String toString() {
        return "ZKConfig{" + "tickTime=" + tickTime + ", dataDir=" + dataDir + ", clientPort=" + clientPort + ", initLimit=" + initLimit + ", syncLimit=" + syncLimit + ", servers=" + servers + '}';
    }
}

class ZKServer {
    public final int srcPort;
    public final int dstPort;
    public final String host;

    @JsonCreator
    public ZKServer(String combo) {
        String[] parts = combo.split(":");
        host = parts[0];
        srcPort = Integer.parseInt(parts[1]);
        dstPort = Integer.parseInt(parts[2]);
    }

    @JsonValue
    public String asString() {
        return String.format("%s:%d:%d", host, srcPort, dstPort);
    }

    @Override
    public String toString() {
        return "ZKServer{" + "srcPort=" + srcPort + ", dstPort=" + dstPort + ", host='" + host + '\'' + '}';
    }
}