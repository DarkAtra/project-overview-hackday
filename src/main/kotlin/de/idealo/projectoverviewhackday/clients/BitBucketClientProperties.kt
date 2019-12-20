package de.idealo.projectoverviewhackday.clients

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank

@Validated
@ConfigurationProperties("clients.bit-bucket")
data class BitBucketClientProperties(
	@field:NotBlank
	var url: String?,
	@field:NotBlank
	var token: String?
)
