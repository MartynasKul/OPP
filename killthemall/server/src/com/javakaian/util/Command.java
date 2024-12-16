package com.javakaian.util;

import java.util.List;

import com.javakaian.network.OServer;

public interface Command<T> {
    void execute(OServer oServer, List<T> entityList);
}