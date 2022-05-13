Cloud Native Sprint in Action

Sections

    • 1 Introduction to cloud native
    • 2 Cloud native patterns and technologies
    • 3 Getting started with cloud native development
    • 4 Externalized configuration management
    • 5 Persisting and managing data in the cloud
    • 6 Containerizing Spring Boot
    • 7 Kubernetes fundamentals for Spring Boot
    • 8 Reactive Spring: Resilience and scalability
    • 9 API gateway and circuit breakers
    • 10 Event-driven applications and functions
    • 11 Security: Authentication and SPA
    • 12 Security: Authorization and auditing
    • 13 Observability and monitoring
    • 14 Configurationand Secretsmanagement
    • 15 GitOps and Kubernetes in production
    • 16 Serverless and Spring Native

Info

Book PDF: file:///C:/Users/ermarty/Documents/Books/Cloud_Native_Spring_in_Action_v11.pdf

Github: https://github.com/ThomasVitale/cloud-native-spring-in-action

Local:
C:\Users\ermarty\helpdocs\courses\Cloud Native Spring in Action
C:\Users\ermarty\source\repos\cloud-native-spring-in-action

Notes

Chapter 1

Cloud native – specifically designed for the cloud and to take advantage of the new environment and of the cloud computing model

Lift and shift – move on the group application to the cloud (does not make it “native”)

Cloud Native Computing Foundation (CNCF) is part of the Linux Foundation


What it means to be cloud native

    • Platforms – application runs on dynamic, distributed environments
    • Properties – scalable, loosely coupled, manageable, observable
    • Practices – automation, continuous delivery

Elasticity, the ability to provision and de-provision resources automatically as needed, was not really possible by traditional IT infrastructures.

IaaS – Infrastructure as a Service, creates VMs (ex. DigitalOcean Droplet, AWS EC2)
• virtual servers
• focus on single computing nodes

CaaS – Container as a service, Docker Swarm, Kubernetes tools used to build container platforms (Amazon Elastic Kubernetes Service EKS)
• container clusters
• focus on clusters of nodes
• underlying infrastructure is abstracted
• deployment target is not a machine but a cluster

PaaS – Platform as a service, you supply executable like JAR file and the platform provides the runtime. Modern example RedHat OpenShift. These services use Kubernetes
• Example: you provide a JAR artifact, the platform takes care of the JRE, middleware, OS, and dependencies

FaaS – function as a service, application triggered by events such as HTTP requests (ex, AWS Lambda, Google Cloud Functions)

SaaS – software as a service, highest abstraction layer, manges whole stack of software and infrastructure (ex. ProtonMail)

Platform = ubuntu machine, Kibernetes Engine, Azure Functions…

Properties

1. Scalability
   Vertical – adding resource to servers like CPU memory
   Horizontal – adding nodes or containers

2. Loose coupling
   High cohesion – encapsulate code that changes together
   Focus on clear interfaces with minimal dependences on others and high cohesion

3. Resilience
   Fault – defect in software or infrastructure that produces incorrect state
   Error – discrepancy between expected behavior vs actual that throws an Exception
   Failure – a fault results in an error that is not caught and the system responds poorly, 500 response

Application should be designed with fault tolerance.
• Ensure error will not cause a failure and a failure will not crash the components of the system or other systems

Self-reparing, self-healing

Fault tolerance techniques – circuit breakers, retries, timeouts, rate limiters

4. Obseravility

How well you can measure internal state from external outputs.

Metrics, logs, traces.

Four Pillars
• Monitoring
• Alerting/visualization
• Distributed systems tracing infrastructure
• Log aggregation/analytics

5. Manageability

External input to change the state or output of a system.

Change applications behavior without changing its code – and how easy it is to apply the changes.

    • Deploying and updating application while keeping the overall system running
    • configuration

data source URLs, service creds, certificates, flags

Manage as much as possible through automation

Practices

Automation

“automate repetitive manual tasks to
accelerate the delivery and deployment of cloud native applications”

Infrastructure as code – defining computing and network infrastructure through source code

    • Terraform – automation tool to spin up VMs and more

Configuration as code – defining configuration for computing resources through source code

    • Ansible – what to install on the VM and app config

Snowflake server – server provisioned manually and is unique, not reproducable, and risky to change

Phoenix server – all tasks acting on server are automated, changes tracked in source control, reproducable

Goal is to create immutable servers

Immutable servers to the extreme when a change is necessary it’s defined in code and a new server is provisioned and the old server is destroyed

Continuous delivery

Software can be released to production at any time

Continuous integration (CI) – frequent commit to main branch which is automatically compiled and tested to quickly detect errors so they can be fixed right away

Continuous delivery (CD) – CI + releasability tests such as acceptance, performance, security, and compliance tests in attempt to increase confidence that software can be released

Deployment pipeline = continuous delivery pipeline
• goes from code committed to releasable outcome – is the only way to production
• keep the main branch of app always in a releasable state

DevOps

DevOps – a culture of working people with different titles working together to deploy and operate a system

“You build it, you run it”

DevOps engineers – proficiency in automation tools, scripting, and IT systems

Goals

It’s not enough to build scalable applications, they need to scale dynamically.

Topologies

Hypervisor
type 1 – vm run on machine hardware
type 1 – host operating system


Virtualization – running a guest OS using a hypervisor, share hardware only. A VM
Containerization – running App on host OS using container runtime layer, share operating system kernel

Docker – an implementation of a Linux container

Orchestration

The deployment target of containers is a VM, the deployment target of orchestrators is a cluster

Container orchestration helps
• manage clusters
• schedule and deploy containers
• health monitoring
• container communication routing
• establishing ports
• application configuration
• security policies

Declared using YAML and other files

Examples of orchestration technologies
• Kubernetes
• Docker Swarm
• Apache Mesos

Serverless

    • focus on implementing business logic for application
    • platforms responsibility to mange and orchestrate the application deployment
    • of course there is a server involved
    • Event driven
    • Scaling to zero – resources are provisioned only when necessary, if there is nothing to process everything is shut down
    • compromise responsibility and scope with control and portability more than any other approach

BaaS – Backend-as-a-service
• Okta authentication
• Google Firebase persistent data
• Amazon API Gateway REST API

FaaS – Function-as-a-service
• stateless application triggered by events
• fully managed by platform
Vendor specific FaaS
• AWS Lambda
• Azure Fucntions
• GC Functions
Open-source solutions
• Knative
◦ serverless runtime environment on top of Kubernetes
◦ used as foundation for enterrpices serverless platforms
▪ RedHat OpenShift Serverless
▪ Google Cloud Run
◦ helps reduce vendor lock-in by allowing serverless workloads to move between platforms and vendors easily
• Apache OpenWhisk

Architectures

Apps – stateless deployable units
Data – stateful databases
Interactions – REST, RPC, etc communication

containers = computation units

Cloud native applications share features to microservices but are not the same.

Service-based architecture

    • main unit of work service that can interact with other services
    • service – provided by a componenet
        ◦ application services 
            ▪ do not hold state
            ▪ do implement logic
        ◦ data services 
            ▪ hold state
            ▪ state – everything that should be preserved when shutting down a service and spinning up a new instance
            ▪ Specifically built for cloud
                • Amazon DynamoDB
                • Azure Cosmos DB
                • Google BigQuery
    • interaction – communication of services

Summary

    • cloud is IT infrastructure provided as a commodity of computing, data storage, networking resources
    • cloud providers deliver services at different levels of abstraction (ex. Infrastructure (IaaS) vs function (FaaS))

Chapter 2

Tweleve-Factor App → 15-Factor methodology

One codebase, one application

    • any shared code should be tracked in its own codebase as a library
    • library can be included as a dependency
    • many deployments in different environments sharing same application artifact
    • no need to re-build codebase to deploy to environment
    • changes to configuration should be outside of the codebase

API first

    • the goal here to design the contract (define the interface) up-front so integrations with other systems can use and depend on the API

Dependency management

    • all declared explicitly
    • able to be downloaded from central repository
    • ex. Gradle, Maven

Design, build, release, run
• Strict separation between stages so don’t change code at runtime

Stages
• design – technologies, dependencies, and tools decided
• build – compile and package with dependencies to immutable artifact called build, uniquely identifiable
• release – build is combined with configuration for deployment, uniquely identifiable using versioning (3.9.4) or timestamp
• run – runs in execution environement

Configuration

    • not part of the codebase, use feature flags
    • if any credentials or environment information would be compromised if codebase became pulic – how to tell if you should keep configuration out of codebase
    • can store default configuration in codebase
    • recommend storing configuration as environment variables

Logs

    • The application does not process logs
    • Logs log to standard output
    • a sequence of events ordered by time
    • external log tool to fetch and collect logs

Disposability

    • treat instances of the application as ephemeral
    • to be started or stopped at any time

Backing services

    • ex. database, other APIs, SMTP
    • treat as attached resources that can be easily changed with configuration (no code change)
    • resource binding

Environment parity
• keep environments as similar as possible

Gaps
• time – code is written vs deployed
• people – follow you build it, you run it philosophy
• tools

Administartive processes

    • one-off processes
    • tracked in revision control
    • delivered together with the application
    • execute in same environemnt
    • triggered by sateless platform possibly via endpoint or by certain events

Port binding

    • one-to-one mapping between application and server
    • don’t depend on external server to run
    • the services provided by the application are exported via port binding, bind HTTP services to specific port

Stateless processing

    • share-nothing architecture – no state should be shared among different application instances
    • not stateless if application is destroyed data would be lost
    • application delegates state management and stoarge to a backing service (like stat stores)

Concurrency

    • concurrent processing to server many users at the same time
    • horizontally scalable
    • only possible if application is stateless
    • JVM handles concurrencty through multiple threads from thread pools
    • processes classified by type: web processes handle HTTP request, worker process executes scheduled jobs in background

Telemetry

    • provide correct data to monitor system’s behavior remotely
    • data logs, metrics, traces, health status, events
    • if your application was a space probe, what would it send back to Earth?

Authentication and authorization

    • follow zero-trust approach
    • authentication standards – Oauth 2.0 and OpenID Connect

Spring

Sprint framework – core of sprint platform

    • supports – dependency injection, transation managemnt, data access, web apps
    • provides execution context – called Spring context or container

Spring boot – quickly build standalone, production-ready applications

Annotations

@SpringBootApplication

    • a shortcut to include three different annotations
    • a Spring configuration class
    • triggers component scanning and Spring Boot auto-configuration


p50Cloud Native Sprint in Action

Sections

    • 1 Introduction to cloud native
    • 2 Cloud native patterns and technologies
    • 3 Getting started with cloud native development
    • 4 Externalized configuration management
    • 5 Persisting and managing data in the cloud
    • 6 Containerizing Spring Boot
    • 7 Kubernetes fundamentals for Spring Boot
    • 8 Reactive Spring: Resilience and scalability
    • 9 API gateway and circuit breakers
    • 10 Event-driven applications and functions
    • 11 Security: Authentication and SPA
    • 12 Security: Authorization and auditing
    • 13 Observability and monitoring
    • 14 Configurationand Secretsmanagement
    • 15 GitOps and Kubernetes in production
    • 16 Serverless and Spring Native

Info

Book PDF: file:///C:/Users/ermarty/Documents/Books/Cloud_Native_Spring_in_Action_v11.pdf

Github: https://github.com/ThomasVitale/cloud-native-spring-in-action

Local:
C:\Users\ermarty\helpdocs\courses\Cloud Native Spring in Action
C:\Users\ermarty\source\repos\cloud-native-spring-in-action

Notes

Chapter 1

Cloud native – specifically designed for the cloud and to take advantage of the new environment and of the cloud computing model

Lift and shift – move on the group application to the cloud (does not make it “native”)

Cloud Native Computing Foundation (CNCF) is part of the Linux Foundation


What it means to be cloud native

    • Platforms – application runs on dynamic, distributed environments
    • Properties – scalable, loosely coupled, manageable, observable
    • Practices – automation, continuous delivery

Elasticity, the ability to provision and de-provision resources automatically as needed, was not really possible by traditional IT infrastructures.

IaaS – Infrastructure as a Service, creates VMs (ex. DigitalOcean Droplet, AWS EC2)
• virtual servers
• focus on single computing nodes

CaaS – Container as a service, Docker Swarm, Kubernetes tools used to build container platforms (Amazon Elastic Kubernetes Service EKS)
• container clusters
• focus on clusters of nodes
• underlying infrastructure is abstracted
• deployment target is not a machine but a cluster

PaaS – Platform as a service, you supply executable like JAR file and the platform provides the runtime. Modern example RedHat OpenShift. These services use Kubernetes
• Example: you provide a JAR artifact, the platform takes care of the JRE, middleware, OS, and dependencies

FaaS – function as a service, application triggered by events such as HTTP requests (ex, AWS Lambda, Google Cloud Functions)

SaaS – software as a service, highest abstraction layer, manges whole stack of software and infrastructure (ex. ProtonMail)

Platform = ubuntu machine, Kibernetes Engine, Azure Functions…

Properties

1. Scalability
   Vertical – adding resource to servers like CPU memory
   Horizontal – adding nodes or containers

2. Loose coupling
   High cohesion – encapsulate code that changes together
   Focus on clear interfaces with minimal dependences on others and high cohesion

3. Resilience
   Fault – defect in software or infrastructure that produces incorrect state
   Error – discrepancy between expected behavior vs actual that throws an Exception
   Failure – a fault results in an error that is not caught and the system responds poorly, 500 response

Application should be designed with fault tolerance.
• Ensure error will not cause a failure and a failure will not crash the components of the system or other systems

Self-reparing, self-healing

Fault tolerance techniques – circuit breakers, retries, timeouts, rate limiters

4. Obseravility

How well you can measure internal state from external outputs.

Metrics, logs, traces.

Four Pillars
• Monitoring
• Alerting/visualization
• Distributed systems tracing infrastructure
• Log aggregation/analytics

5. Manageability

External input to change the state or output of a system.

Change applications behavior without changing its code – and how easy it is to apply the changes.

    • Deploying and updating application while keeping the overall system running
    • configuration

data source URLs, service creds, certificates, flags

Manage as much as possible through automation

Practices

Automation

“automate repetitive manual tasks to
accelerate the delivery and deployment of cloud native applications”

Infrastructure as code – defining computing and network infrastructure through source code

    • Terraform – automation tool to spin up VMs and more

Configuration as code – defining configuration for computing resources through source code

    • Ansible – what to install on the VM and app config

Snowflake server – server provisioned manually and is unique, not reproducable, and risky to change

Phoenix server – all tasks acting on server are automated, changes tracked in source control, reproducable

Goal is to create immutable servers

Immutable servers to the extreme when a change is necessary it’s defined in code and a new server is provisioned and the old server is destroyed

Continuous delivery

Software can be released to production at any time

Continuous integration (CI) – frequent commit to main branch which is automatically compiled and tested to quickly detect errors so they can be fixed right away

Continuous delivery (CD) – CI + releasability tests such as acceptance, performance, security, and compliance tests in attempt to increase confidence that software can be released

Deployment pipeline = continuous delivery pipeline
• goes from code committed to releasable outcome – is the only way to production
• keep the main branch of app always in a releasable state

DevOps

DevOps – a culture of working people with different titles working together to deploy and operate a system

“You build it, you run it”

DevOps engineers – proficiency in automation tools, scripting, and IT systems

Goals

It’s not enough to build scalable applications, they need to scale dynamically.

Topologies

Hypervisor
type 1 – vm run on machine hardware
type 1 – host operating system


Virtualization – running a guest OS using a hypervisor, share hardware only. A VM
Containerization – running App on host OS using container runtime layer, share operating system kernel

Docker – an implementation of a Linux container

Orchestration

The deployment target of containers is a VM, the deployment target of orchestrators is a cluster

Container orchestration helps
• manage clusters
• schedule and deploy containers
• health monitoring
• container communication routing
• establishing ports
• application configuration
• security policies

Declared using YAML and other files

Examples of orchestration technologies
• Kubernetes
• Docker Swarm
• Apache Mesos

Serverless

    • focus on implementing business logic for application
    • platforms responsibility to mange and orchestrate the application deployment
    • of course there is a server involved
    • Event driven
    • Scaling to zero – resources are provisioned only when necessary, if there is nothing to process everything is shut down
    • compromise responsibility and scope with control and portability more than any other approach

BaaS – Backend-as-a-service
• Okta authentication
• Google Firebase persistent data
• Amazon API Gateway REST API

FaaS – Function-as-a-service
• stateless application triggered by events
• fully managed by platform
Vendor specific FaaS
• AWS Lambda
• Azure Fucntions
• GC Functions
Open-source solutions
• Knative
◦ serverless runtime environment on top of Kubernetes
◦ used as foundation for enterrpices serverless platforms
▪ RedHat OpenShift Serverless
▪ Google Cloud Run
◦ helps reduce vendor lock-in by allowing serverless workloads to move between platforms and vendors easily
• Apache OpenWhisk

Architectures

Apps – stateless deployable units
Data – stateful databases
Interactions – REST, RPC, etc communication

containers = computation units

Cloud native applications share features to microservices but are not the same.

Service-based architecture

    • main unit of work service that can interact with other services
    • service – provided by a componenet
        ◦ application services 
            ▪ do not hold state
            ▪ do implement logic
        ◦ data services 
            ▪ hold state
            ▪ state – everything that should be preserved when shutting down a service and spinning up a new instance
            ▪ Specifically built for cloud
                • Amazon DynamoDB
                • Azure Cosmos DB
                • Google BigQuery
    • interaction – communication of services

Summary

    • cloud is IT infrastructure provided as a commodity of computing, data storage, networking resources
    • cloud providers deliver services at different levels of abstraction (ex. Infrastructure (IaaS) vs function (FaaS))

Chapter 2

Tweleve-Factor App → 15-Factor methodology

One codebase, one application

    • any shared code should be tracked in its own codebase as a library
    • library can be included as a dependency
    • many deployments in different environments sharing same application artifact
    • no need to re-build codebase to deploy to environment
    • changes to configuration should be outside of the codebase

API first

    • the goal here to design the contract (define the interface) up-front so integrations with other systems can use and depend on the API

Dependency management

    • all declared explicitly
    • able to be downloaded from central repository
    • ex. Gradle, Maven

Design, build, release, run
• Strict separation between stages so don’t change code at runtime

Stages
• design – technologies, dependencies, and tools decided
• build – compile and package with dependencies to immutable artifact called build, uniquely identifiable
• release – build is combined with configuration for deployment, uniquely identifiable using versioning (3.9.4) or timestamp
• run – runs in execution environement

Configuration

    • not part of the codebase, use feature flags
    • if any credentials or environment information would be compromised if codebase became pulic – how to tell if you should keep configuration out of codebase
    • can store default configuration in codebase
    • recommend storing configuration as environment variables

Logs

    • The application does not process logs
    • Logs log to standard output
    • a sequence of events ordered by time
    • external log tool to fetch and collect logs

Disposability

    • treat instances of the application as ephemeral
    • to be started or stopped at any time

Backing services

    • ex. database, other APIs, SMTP
    • treat as attached resources that can be easily changed with configuration (no code change)
    • resource binding

Environment parity
• keep environments as similar as possible

Gaps
• time – code is written vs deployed
• people – follow you build it, you run it philosophy
• tools

Administartive processes

    • one-off processes
    • tracked in revision control
    • delivered together with the application
    • execute in same environemnt
    • triggered by sateless platform possibly via endpoint or by certain events

Port binding

    • one-to-one mapping between application and server
    • don’t depend on external server to run
    • the services provided by the application are exported via port binding, bind HTTP services to specific port

Stateless processing

    • share-nothing architecture – no state should be shared among different application instances
    • not stateless if application is destroyed data would be lost
    • application delegates state management and stoarge to a backing service (like stat stores)

Concurrency

    • concurrent processing to server many users at the same time
    • horizontally scalable
    • only possible if application is stateless
    • JVM handles concurrencty through multiple threads from thread pools
    • processes classified by type: web processes handle HTTP request, worker process executes scheduled jobs in background

Telemetry

    • provide correct data to monitor system’s behavior remotely
    • data logs, metrics, traces, health status, events
    • if your application was a space probe, what would it send back to Earth?

Authentication and authorization

    • follow zero-trust approach
    • authentication standards – Oauth 2.0 and OpenID Connect

Spring

Sprint framework – core of sprint platform

    • supports – dependency injection, transation managemnt, data access, web apps
    • provides execution context – called Spring context or container

Spring boot – quickly build standalone, production-ready applications

Annotations

@SpringBootApplication

    • a shortcut to include three different annotations
    • a Spring configuration class
    • triggers component scanning and Spring Boot auto-configuration


p50Cloud Native Sprint in Action

Sections

    • 1 Introduction to cloud native
    • 2 Cloud native patterns and technologies
    • 3 Getting started with cloud native development
    • 4 Externalized configuration management
    • 5 Persisting and managing data in the cloud
    • 6 Containerizing Spring Boot
    • 7 Kubernetes fundamentals for Spring Boot
    • 8 Reactive Spring: Resilience and scalability
    • 9 API gateway and circuit breakers
    • 10 Event-driven applications and functions
    • 11 Security: Authentication and SPA
    • 12 Security: Authorization and auditing
    • 13 Observability and monitoring
    • 14 Configurationand Secretsmanagement
    • 15 GitOps and Kubernetes in production
    • 16 Serverless and Spring Native

Info

Book PDF: file:///C:/Users/ermarty/Documents/Books/Cloud_Native_Spring_in_Action_v11.pdf

Github: https://github.com/ThomasVitale/cloud-native-spring-in-action

Local:
C:\Users\ermarty\helpdocs\courses\Cloud Native Spring in Action
C:\Users\ermarty\source\repos\cloud-native-spring-in-action

Notes

Chapter 1

Cloud native – specifically designed for the cloud and to take advantage of the new environment and of the cloud computing model

Lift and shift – move on the group application to the cloud (does not make it “native”)

Cloud Native Computing Foundation (CNCF) is part of the Linux Foundation


What it means to be cloud native

    • Platforms – application runs on dynamic, distributed environments
    • Properties – scalable, loosely coupled, manageable, observable
    • Practices – automation, continuous delivery

Elasticity, the ability to provision and de-provision resources automatically as needed, was not really possible by traditional IT infrastructures.

IaaS – Infrastructure as a Service, creates VMs (ex. DigitalOcean Droplet, AWS EC2)
• virtual servers
• focus on single computing nodes

CaaS – Container as a service, Docker Swarm, Kubernetes tools used to build container platforms (Amazon Elastic Kubernetes Service EKS)
• container clusters
• focus on clusters of nodes
• underlying infrastructure is abstracted
• deployment target is not a machine but a cluster

PaaS – Platform as a service, you supply executable like JAR file and the platform provides the runtime. Modern example RedHat OpenShift. These services use Kubernetes
• Example: you provide a JAR artifact, the platform takes care of the JRE, middleware, OS, and dependencies

FaaS – function as a service, application triggered by events such as HTTP requests (ex, AWS Lambda, Google Cloud Functions)

SaaS – software as a service, highest abstraction layer, manges whole stack of software and infrastructure (ex. ProtonMail)

Platform = ubuntu machine, Kibernetes Engine, Azure Functions…

Properties

1. Scalability
   Vertical – adding resource to servers like CPU memory
   Horizontal – adding nodes or containers

2. Loose coupling
   High cohesion – encapsulate code that changes together
   Focus on clear interfaces with minimal dependences on others and high cohesion

3. Resilience
   Fault – defect in software or infrastructure that produces incorrect state
   Error – discrepancy between expected behavior vs actual that throws an Exception
   Failure – a fault results in an error that is not caught and the system responds poorly, 500 response

Application should be designed with fault tolerance.
• Ensure error will not cause a failure and a failure will not crash the components of the system or other systems

Self-reparing, self-healing

Fault tolerance techniques – circuit breakers, retries, timeouts, rate limiters

4. Obseravility

How well you can measure internal state from external outputs.

Metrics, logs, traces.

Four Pillars
• Monitoring
• Alerting/visualization
• Distributed systems tracing infrastructure
• Log aggregation/analytics

5. Manageability

External input to change the state or output of a system.

Change applications behavior without changing its code – and how easy it is to apply the changes.

    • Deploying and updating application while keeping the overall system running
    • configuration

data source URLs, service creds, certificates, flags

Manage as much as possible through automation

Practices

Automation

“automate repetitive manual tasks to
accelerate the delivery and deployment of cloud native applications”

Infrastructure as code – defining computing and network infrastructure through source code

    • Terraform – automation tool to spin up VMs and more

Configuration as code – defining configuration for computing resources through source code

    • Ansible – what to install on the VM and app config

Snowflake server – server provisioned manually and is unique, not reproducable, and risky to change

Phoenix server – all tasks acting on server are automated, changes tracked in source control, reproducable

Goal is to create immutable servers

Immutable servers to the extreme when a change is necessary it’s defined in code and a new server is provisioned and the old server is destroyed

Continuous delivery

Software can be released to production at any time

Continuous integration (CI) – frequent commit to main branch which is automatically compiled and tested to quickly detect errors so they can be fixed right away

Continuous delivery (CD) – CI + releasability tests such as acceptance, performance, security, and compliance tests in attempt to increase confidence that software can be released

Deployment pipeline = continuous delivery pipeline
• goes from code committed to releasable outcome – is the only way to production
• keep the main branch of app always in a releasable state

DevOps

DevOps – a culture of working people with different titles working together to deploy and operate a system

“You build it, you run it”

DevOps engineers – proficiency in automation tools, scripting, and IT systems

Goals

It’s not enough to build scalable applications, they need to scale dynamically.

Topologies

Hypervisor
type 1 – vm run on machine hardware
type 1 – host operating system


Virtualization – running a guest OS using a hypervisor, share hardware only. A VM
Containerization – running App on host OS using container runtime layer, share operating system kernel

Docker – an implementation of a Linux container

Orchestration

The deployment target of containers is a VM, the deployment target of orchestrators is a cluster

Container orchestration helps
• manage clusters
• schedule and deploy containers
• health monitoring
• container communication routing
• establishing ports
• application configuration
• security policies

Declared using YAML and other files

Examples of orchestration technologies
• Kubernetes
• Docker Swarm
• Apache Mesos

Serverless

    • focus on implementing business logic for application
    • platforms responsibility to mange and orchestrate the application deployment
    • of course there is a server involved
    • Event driven
    • Scaling to zero – resources are provisioned only when necessary, if there is nothing to process everything is shut down
    • compromise responsibility and scope with control and portability more than any other approach

BaaS – Backend-as-a-service
• Okta authentication
• Google Firebase persistent data
• Amazon API Gateway REST API

FaaS – Function-as-a-service
• stateless application triggered by events
• fully managed by platform
Vendor specific FaaS
• AWS Lambda
• Azure Fucntions
• GC Functions
Open-source solutions
• Knative
◦ serverless runtime environment on top of Kubernetes
◦ used as foundation for enterrpices serverless platforms
▪ RedHat OpenShift Serverless
▪ Google Cloud Run
◦ helps reduce vendor lock-in by allowing serverless workloads to move between platforms and vendors easily
• Apache OpenWhisk

Architectures

Apps – stateless deployable units
Data – stateful databases
Interactions – REST, RPC, etc communication

containers = computation units

Cloud native applications share features to microservices but are not the same.

Service-based architecture

    • main unit of work service that can interact with other services
    • service – provided by a componenet
        ◦ application services 
            ▪ do not hold state
            ▪ do implement logic
        ◦ data services 
            ▪ hold state
            ▪ state – everything that should be preserved when shutting down a service and spinning up a new instance
            ▪ Specifically built for cloud
                • Amazon DynamoDB
                • Azure Cosmos DB
                • Google BigQuery
    • interaction – communication of services

Summary

    • cloud is IT infrastructure provided as a commodity of computing, data storage, networking resources
    • cloud providers deliver services at different levels of abstraction (ex. Infrastructure (IaaS) vs function (FaaS))

Chapter 2

Tweleve-Factor App → 15-Factor methodology

One codebase, one application

    • any shared code should be tracked in its own codebase as a library
    • library can be included as a dependency
    • many deployments in different environments sharing same application artifact
    • no need to re-build codebase to deploy to environment
    • changes to configuration should be outside of the codebase

API first

    • the goal here to design the contract (define the interface) up-front so integrations with other systems can use and depend on the API

Dependency management

    • all declared explicitly
    • able to be downloaded from central repository
    • ex. Gradle, Maven

Design, build, release, run
• Strict separation between stages so don’t change code at runtime

Stages
• design – technologies, dependencies, and tools decided
• build – compile and package with dependencies to immutable artifact called build, uniquely identifiable
• release – build is combined with configuration for deployment, uniquely identifiable using versioning (3.9.4) or timestamp
• run – runs in execution environement

Configuration

    • not part of the codebase, use feature flags
    • if any credentials or environment information would be compromised if codebase became pulic – how to tell if you should keep configuration out of codebase
    • can store default configuration in codebase
    • recommend storing configuration as environment variables

Logs

    • The application does not process logs
    • Logs log to standard output
    • a sequence of events ordered by time
    • external log tool to fetch and collect logs

Disposability

    • treat instances of the application as ephemeral
    • to be started or stopped at any time

Backing services

    • ex. database, other APIs, SMTP
    • treat as attached resources that can be easily changed with configuration (no code change)
    • resource binding

Environment parity
• keep environments as similar as possible

Gaps
• time – code is written vs deployed
• people – follow you build it, you run it philosophy
• tools

Administartive processes

    • one-off processes
    • tracked in revision control
    • delivered together with the application
    • execute in same environemnt
    • triggered by sateless platform possibly via endpoint or by certain events

Port binding

    • one-to-one mapping between application and server
    • don’t depend on external server to run
    • the services provided by the application are exported via port binding, bind HTTP services to specific port

Stateless processing

    • share-nothing architecture – no state should be shared among different application instances
    • not stateless if application is destroyed data would be lost
    • application delegates state management and stoarge to a backing service (like stat stores)

Concurrency

    • concurrent processing to server many users at the same time
    • horizontally scalable
    • only possible if application is stateless
    • JVM handles concurrencty through multiple threads from thread pools
    • processes classified by type: web processes handle HTTP request, worker process executes scheduled jobs in background

Telemetry

    • provide correct data to monitor system’s behavior remotely
    • data logs, metrics, traces, health status, events
    • if your application was a space probe, what would it send back to Earth?

Authentication and authorization

    • follow zero-trust approach
    • authentication standards – Oauth 2.0 and OpenID Connect

Spring

Sprint framework – core of sprint platform

    • supports – dependency injection, transation managemnt, data access, web apps
    • provides execution context – called Spring context or container

Spring boot – quickly build standalone, production-ready applications

Annotations

@SpringBootApplication

    • a shortcut to include three different annotations
    • a Spring configuration class
    • triggers component scanning and Spring Boot auto-configuration

Containerizing

Open Container Initiative (OCI)

- OCI Runtime Specification - defines how to run container images
- OCI Image Specification - defines how to build container images
- OCI Distribution Specification - defines how to distribute them
- Docker is OCI compliant
- Docker is a founding member of OCI

Docker

Docker server
 
- contains Docker daemon, background process that creates and manages docker images and other objects
- the server can be called the docker daemon, they are interchangeable
- exposes an API that you can run containers through

Docker host

- the machine where Docker server runs

Docker client

- talks to daemon through the API
- command-line based

Container registry

- host and distribute Docker containers, a registry
- distingish between public and private registries
- Docker Hub is default public registry provided by Docker company

Container image

- lightweight executable package
- can be created from scratch by defining text-based Dockerfile
- versioned using colon, ex. ubuntu:20.04

Container

- a runnable instance of a container image
- start, stop, update, delete
- can expose services to the outside world through port forwarding or port mapping

Build Docker image using Gradle

- build docker image around running JAR build of Java project

$ ./gradlew bootBuildImage

How to run image

$ docker run --rm --name catalog-service -p 8080:8080 catalog-service:0.0.1-SNAPSHOT