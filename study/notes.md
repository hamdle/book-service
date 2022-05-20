Cloud Native Sprint in Action

Sections

1. ~~1 Introduction to cloud native~~
2. ~~Cloud native patterns and technologies~~
3. Getting started with cloud native development
4. Externalized configuration management
5. Persisting and managing data in the cloud
6. Containerizing Spring Boot
7. Kubernetes fundamentals for Spring Boot
8. Reactive Spring: Resilience and scalability
9. API gateway and circuit breakers
10. Event-driven applications and functions
11. Security: Authentication and SPA
12. Security: Authorization and auditing
13. Observability and monitoring
14. Configurationand Secretsmanagement
15. GitOps and Kubernetes in production
16. Serverless and Spring Native

Info

Book PDF: file:///C:/Users/ermarty/Documents/Books/Cloud_Native_Spring_in_Action_v11.pdf

Github: https://github.com/ThomasVitale/cloud-native-spring-in-action

Local:
C:\Users\ermarty\helpdocs\courses\Cloud Native Spring in Action
C:\Users\ermarty\source\repos\cloud-native-spring-in-action

Notes

## Chapter 1

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

## Chapter 2

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

Kubernetes

- standard for container orchestration
- shortened as K8s
- automate deployment, scaling, management of containerized applications
- deployment target is a machine (ex, dev computer, VM)

Minikube

- run a local Kubernetes cluster on any operating system
- maintained by Kubernetes community
- by default does not have access to local Docker registry

Cluster

- a set of worker machines (nodes) that run containerized applications
- every cluster has at least one worker node
- minikube can create clusters and single-node clusters
- in production cloud provider will create clusters

Worker nodes

- Kubernetes cluster comprises machines called worker nodes
- containerized applications are deployed to worker nodes
- provide cpu, memory, network, and storage
- client doesn't interact with worker nodes directly, uses kubectl

Control plane

- container orchestration layer
- manages worker nodes
- exposes API and interfaces to define, deploy, and manage the lifecycle of containers
- implements features of orchestration
  - cluster management
  - scheduling
  - health monitoring

$ kubectl

- Kubernetes CLI client
- communicates with control plane to perform operations on worker nodes

Pod

- smallest deployable unit
- contains only one of your application
- kubernetes manages pods rather than containers directly

Deployment

- informs kubernetes about the desired deployment state of application
- creates pod and keeps healthy for each deployment instance

Service

- a deployment
- a set of pods
- can be exposed to other nodes or clusters or outside by defining a Service

Run

1. create resource manifest
2. describe state for application
3. YAML file
4. use kubectl client to ask the control plane to create the resource described by the manifest
5. control plane creates resource in worker nodes
6. control plane rely on container registry to fetch images defined in resource manifest

Manually import docker image into minikube

$ minikube image load catalog-service:0.0.1-SNAPSHOT

Create Deployment resource

$ kubectl create deployment catalog-service --image=catalog-service:0.0.1-SNAPSHOT

Verfiy deployment

$ kubectl get deployment

Verfiy pod creation

$ kubectl get pod

Applications running in Kubernetes are not accessible by default

Expose application to the cluster through a Service resource

$ kubectl expose deployment catalog-service --name=catalog-service --port=8080

Verify Service

$ kubectl get service

Port forwarding

- forward traffic from local port 8000 to exposed Service port 8080

$ kubectl port-forward service/catalog-service 8000:8080

To destroy

$ kubectl delete service catalog-service
$ kubectl delete deployment catalog-service
$ minikube stop

## Chapter 3

Embedded servers

- Spring boot using Tomcat

Tomcat

- a component of a web server
- provides execution context for web app using Java Servlet API

Port binding

- cloud: self-contained and export via binding a port that can be configured
- traditional: external server required to execute

Concurrency

- handle concurrency through multiple threads via thread pools
- favor horizontal over vertical scaling
   - aka deploy more instances and distribute workload as opposed to adding more resources to server

Executable JARs and embedded servers

- the solution is to bring the server capabilities into the application itself
- this is saved as JAR instead of EAR or WAR
- fat-JAR or uber-JAR is a JAR with dependencies and server bundled into the single JAR executable

./gradlew bootJar

- compile code and package application
- saves in "build/libs/"
- run: java -jar build/libs/[name-of-file].jar

./gradlew build

- combines bootJar and test

Understanding the thread-per-request model

synchronous

- blocking
- scheduled

thread-per-request

- model used by Tomcat server
- dedicates a thread to handle a single response
- waits for other processes to finish before continuing (synchronous)

Tomcat

- initalizes with a thread pool ready to use to handle incoming request
- new requests will be queued until a thread is ready when all threads are in use
- so the number of threads defined by tomcat is the number of requests it can handle at once (concurrently)
- this is not always the most efficient method of using all server resources
- asyncrounous alternatives exists like Spring WebFlux and Project Reactor

Configuring the embedded Tomcat

Normal configuration

- edit server.xml or context.xml

Spring boot Tomcat

- you can configure it 2 ways
1. through properties
2. or in a WebServerFactoryCustomizer bean

Properties

- src/main/resource
  - application.properties
  - application.yml

server.tomcat.connection-timeout

- how long to wait between accepting TCP connection and reciving HTTP request
- keep low, especially in cloud environment, to prevent DDoS attack. 2s

server.tomcat.keep-alive-timeout

- limit the time spent reading the HTTP request. 15s

Building a RESTful application with Spring MVC

API first pattern

- define the API contract first
- business logic later
- requirements should already be defined
- documenting the API

Business logic

Entity - also called Domain Entity, represents a noun in the domain, book

Service - defines a use case for the domain (ex. adding a book to the catalog)

Repository - abstraction to let domain layer access data independently of its source

Testing the API

- use "http" command line tool

POST Request

- $ http POST :9001/books author="Lisa Smithongue" title="My Cat Lights the Way" isbn="1234" price=12.50

GET Resquest

- $ http :9001/books/1234




