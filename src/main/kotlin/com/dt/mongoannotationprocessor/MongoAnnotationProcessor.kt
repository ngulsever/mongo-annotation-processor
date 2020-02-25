/*
 * Copyright 2011-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dt.mongoannotationprocessor

import com.querydsl.apt.AbstractQuerydslProcessor
import com.querydsl.apt.Configuration
import com.querydsl.apt.DefaultConfiguration
import com.querydsl.core.annotations.QueryEmbeddable
import com.querydsl.core.annotations.QueryEmbedded
import com.querydsl.core.annotations.QueryEntities
import com.querydsl.core.annotations.QuerySupertype
import com.querydsl.core.annotations.QueryTransient
import org.springframework.data.mongodb.core.mapping.Document
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.tools.Diagnostic

/**
 * Annotation processor to create Querydsl query types for QueryDsl annotated classes.
 *
 * @author Oliver Gierke
 * @author Owen Q
 */
@SupportedAnnotationTypes("com.querydsl.core.annotations.*", "org.springframework.data.mongodb.core.mapping.*")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
class MongoAnnotationProcessor : AbstractQuerydslProcessor() {
    override fun createConfiguration(roundEnv: RoundEnvironment): Configuration {
        processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, "Running " + javaClass.simpleName)

        val configuration = DefaultConfiguration(
            roundEnv, processingEnv.options, emptySet(),
            QueryEntities::class.java, Document::class.java,
            QuerySupertype::class.java,
            QueryEmbeddable::class.java,
            QueryEmbedded::class.java,
            QueryTransient::class.java
        )
        configuration.isUnknownAsEmbedded = true

        return configuration
    }
}