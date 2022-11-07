package com.github.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

/**
 * @author bin
 * @since 2022/11/07
 */

inline fun <reified T : Any> getLogger(): Logger = getLogger(T::class)
fun getLogger(clazz: KClass<*>): Logger = LoggerFactory.getLogger(clazz.java)
