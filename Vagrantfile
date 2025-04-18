# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|

  vagrant_root = File.dirname(__FILE__)
  ENV['ANSIBLE_ROLES_PATH'] = "#{vagrant_root}/..:#{vagrant_root}/tests/roles"
  ENV['VAGRANT_DEFAULT_PROVIDER'] = 'libvirt'

  config.vm.define "jenkins-master" do |config|
    config.vm.hostname = "jenkins-master"
    config.vm.box      = "oraclelinux/9"
    config.vm.box_url  = "https://oracle.github.io/vagrant-projects/boxes/oraclelinux/9.json"
    config.vm.provider :libvirt do |libvirt|
      libvirt.cpus                      = 2
      libvirt.memory                    = 4 * 1024
      libvirt.qemu_use_session          = false
      libvirt.management_network_name   = 'vagrant'
      libvirt.management_network_domain = 'local'
    end
    config.vm.synced_folder ".", "/vagrant", disabled: true
    config.vm.provision "ansible" do |ansible|
      ansible.host_key_checking  = false
      ansible.compatibility_mode = "2.0"
      ansible.become             = true
      ansible.playbook           = "tests/playbook.yml"  
      ansible.limit              = "all"
    end
  end

end
