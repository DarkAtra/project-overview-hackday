package de.darkatra.projectoverview.git

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(RepositoryServiceProperties::class)
class RepositoryServiceConfiguration