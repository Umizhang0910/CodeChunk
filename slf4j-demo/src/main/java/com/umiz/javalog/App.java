package com.umiz.javalog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App
{
    final static Logger logger = LoggerFactory.getLogger("demo");
    public static void main( String[] args )
    {
        logger.info("Hello World!");
    }
}
