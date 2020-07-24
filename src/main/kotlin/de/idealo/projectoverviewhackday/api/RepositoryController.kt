package de.idealo.projectoverviewhackday.api

import de.idealo.projectoverviewhackday.api.model.RepositoryCreateRequest
import de.idealo.projectoverviewhackday.api.model.RepositoryDeleteRequest
import de.idealo.projectoverviewhackday.api.model.RepositoryResponse
import de.idealo.projectoverviewhackday.base.RepositoryService
import de.idealo.projectoverviewhackday.base.model.Repository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@CrossOrigin(origins = ["\${projectoverview.security.allowed-origins}"])
@RequestMapping("/repositories", produces = [MediaType.APPLICATION_JSON_VALUE])
class RepositoryController(
	private val repositoryService: RepositoryService
) {

	@GetMapping
	fun getRepositories(): List<RepositoryResponse> {

		return repositoryService.getRepositories()
			.map { repository ->
				RepositoryResponse(
					name = repository.name,
					browseUrl = repository.browseUrl,
					cloneUrl = repository.cloneUrl,
					checks = repository.checks
				)
			}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	fun createRepository(@Valid @RequestBody repositoryCreateRequest: RepositoryCreateRequest) {

		repositoryService.createRepository(
			Repository(
				name = repositoryCreateRequest.name,
				browseUrl = repositoryCreateRequest.browseUrl,
				cloneUrl = repositoryCreateRequest.cloneUrl,
				checks = repositoryCreateRequest.checks
			)
		)
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	fun deleteRepository(@Valid @RequestBody repositoryDeleteRequest: RepositoryDeleteRequest) {

		repositoryService.deleteRepository(repositoryDeleteRequest.name)
	}
}