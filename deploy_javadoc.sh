#!/bin/bash
set -ex

repository="git@github.com:egugue/GitHaru.git"
workspace=workspace

presentation="presentation"
infra="infra"
domain="domain"

# Delete any existing workspace
rm -rf $workspace

# Clone the current repo into temp folder
git clone $repository $workspace

# Move working directory into temp folder
cd $workspace

# Checkout and track the gh-pages branch
git checkout -t origin/gh-pages

# Delete dir.
rm -rf ./javadoc

# Act each project.
for project in $presentation $domain $infra
do
    # Make javadoc directory if needed
    git_javadoc_dir=./javadoc/${project}
    mkdir -p ${git_javadoc_dir}

    # Sync javadoc from real repo
    local_javadoc_dir="./${project}/build/docs/javadoc/"
    rsync -avr --delete ../${local_javadoc_dir} ./${git_javadoc_dir}
done

# Stage all files in git and create a commit
git add .
git add -u
git commit -m "Javadoc at $(date)"

# Push the new files up to GitHub
git push origin gh-pages

# Delete our workspace folder
cd ..
rm -rf $workspace
