# Ansible Role: Jenkins

This Ansible role installs and configures Jenkins.

## Requirements

* Ansible 2.15 or higher
* Supported operating systems:
    * OracleLinux 9  
    * AlmaLinux 9  

## Playbook example
### Clean install

```yaml
---
- hosts: jenkins
  become: yes

  tasks:

  - ansible.builtin.include_role:
      name: jenkins

```

### [Configuration as Code](https://plugins.jenkins.io/configuration-as-code) and additional plugins
```yaml
---
- hosts: jenkins
  become: yes

  tasks:

  - ansible.builtin.include_role:
      name: jenkins
    vars:
      jenkins_java_pkg: java-21-openjdk
      jenkins_version: 2.492.3
      jenkins_admin_user: "admin"
      jenkins_admin_user_pass: "password"
      jenkins_casc_install: true
      jenkins_plugins:
      - name: job-dsl
      - name: workflow-aggregator
      - name: docker-workflow
      - name: pipeline-stage-view
      - name: git
      - name: git-parameter
      - name: script-security
      - name: configuration-as-code
      jenkins_casc_config:
        jenkins:
          systemMessage: "Jenkins configured by Jenkins Configuration as Code plugin"
          numExecutors: 1
          securityRealm:
            local:
              allowsSignup: false
              users:
              - id: "{{ jenkins_admin_user }}"
                password: "{{ jenkins_admin_user_pass }}"
        security:
          globalJobDslSecurityConfiguration:
            useScriptSecurity: false

```

### TEST
```bash
molecule test --all
```
