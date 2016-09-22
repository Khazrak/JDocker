package se.codeslasher.docker.model.api124;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

import java.util.Iterator;
import java.util.List;

/**
 * Created by karl on 9/22/16.
 */
@Getter
@Builder
@JsonDeserialize(builder = Warnings.WarningsBuilder.class)
public class Warnings implements Iterable<String> {

    @JsonProperty("Warnings")
    private List<String> warnings;

    public String getWarning(int index) {
        return warnings.get(index);
    }

    public int size() {
        return warnings.size();
    }

    @Override
    public Iterator<String> iterator() {
        return new WarningsIterator();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class WarningsBuilder {

        @JsonProperty("Warnings")
        private List<String> warnings;

    }

    public class WarningsIterator implements Iterator<String>  {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index <= warnings.size();
        }

        @Override
        public String next() {
            return warnings.get(index++);
        }
    }

}
