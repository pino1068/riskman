# -*- encoding: utf-8 -*-

Gem::Specification.new do |s|
  s.name = %q{riskman}
  s.version = "0.0.16"

  s.required_rubygems_version = Gem::Requirement.new(">= 0") if s.respond_to? :required_rubygems_version=
  s.authors = ["Luca Marrocco, Giuseppe Di Pierri"]
  s.date = %q{2010-02-08}
  s.default_executable = %q{riskman}
  s.description = %q{FIX (describe your package)}
  s.email = ["riskman-dev@googlegroups.com"]
  s.executables = ["riskman"]
  s.files = ["README.rdoc", "bin/riskman", "lib/riskman.rb", "lib/riskman.jar", "lib/hamcrest-all-1.1.jar", "test/test_helper.rb", "test/test_riskman.rb"]
  s.homepage = %q{http://github.com/#{github_username}/#{project_name}}
  s.post_install_message = %q{PostInstall.txt}
  s.rdoc_options = ["--main", "README.rdoc"]
  s.require_paths = ["lib"]
  s.rubyforge_project = %q{riskman}
  s.rubygems_version = %q{1.3.5}
  s.summary = %q{FIX (describe your package)}
  s.test_files = ["test/test_helper.rb", "test/test_riskman.rb"]

  if s.respond_to? :specification_version then
    current_version = Gem::Specification::CURRENT_SPECIFICATION_VERSION
    s.specification_version = 3

    if Gem::Version.new(Gem::RubyGemsVersion) >= Gem::Version.new('1.2.0') then
      s.add_development_dependency(%q<hoe>, [">= 2.3.3"])
    else
      s.add_dependency(%q<hoe>, [">= 2.3.3"])
    end
  else
    s.add_dependency(%q<hoe>, [">= 2.3.3"])
  end
end
