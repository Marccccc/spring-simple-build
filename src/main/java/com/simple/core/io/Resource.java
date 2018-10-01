package com.simple.core.io;

import java.io.InputStream;

public interface Resource {

    InputStream getInputStream();

    String getDescribe();

}
