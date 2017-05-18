.PHONY: all jar image

REPO=michaelvk1994

# Name of the image
IMAGE=ipsenh-studievolg

# Current branch-commit (example: master-ab01c1z)
CURRENT=`echo $$GIT_BRANCH | cut -d'/' -f 2-`-$$(git rev-parse HEAD | cut -c1-7)


#Jenkins step to run complete pipeline
ci-jenkins-tests:
	docker run --rm -v $$WORKSPACE:/opt/dropwizard -w /opt/dropwizard maven:3.5.0-jdk-8-alpine mvn install

# Jenins stept to run complete pipeline
ci-jenkins: ci-jenkins-tests build push cleanup

# Create docker image with tag michaelvk1994/ipsenh-studievolg:branch-sha
build:
	docker build -t $(REPO)/$(IMAGE):$(CURRENT) -f operations/docker/Dockerfile .

# Push image to the hub, this also build the image
push: build
	docker push $(REPO)/$(IMAGE):$(CURRENT)

# Cleanup step to remove test image and build image
cleanup:
	docker rmi $(IMAGE):test
	docker rmi $(REPO)/$(IMAGE):$(CURRENT)

# Run development via docker-compose. This autoreloads/compiles on change etc.
start:
	docker-compose up -d
