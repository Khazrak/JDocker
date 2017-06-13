package com.github.khazrak.jdocker.api129.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.github.khazrak.jdocker.abstraction.Warnings;
import lombok.Builder;
import lombok.Getter;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Getter
@Builder
@JsonDeserialize(builder = Warnings129.Warnings129Builder.class)
public class Warnings129 implements Warnings, Iterable<String> {

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
    public static class Warnings129Builder {

        @JsonProperty("Warnings")
        private List<String> warnings;

    }

    public class WarningsIterator implements Iterator<String> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index <= warnings.size();
        }

        @Override
        public String next () {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            return warnings.get(index++);
        }
    }

}
