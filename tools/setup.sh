#!/bin/bash

RED='\033[0;1;31m'
NC='\033[0m' # No Color

GIT_DIR=$(git rev-parse --git-dir 2> /dev/null)
GIT_ROOT=$(git rev-parse --show-toplevel 2> /dev/null)

if [[ ! "$GIT_ROOT" =~ /Loteria-Mexicana$ ]]; then
  echo -e "${RED}ERROR:${NC} Please run this script from the cloned Loteria-Mexicana directory."
  exit 1
fi

#echo "Installing git commit-message hook"
#echo
#curl -sSLo "${GIT_DIR}/hooks/commit-msg" \
#    "https://gerrit-review.googlesource.com/tools/hooks/commit-msg" \
#  && chmod +x "${GIT_DIR}/hooks/commit-msg"

#echo "Installing git pre-push hook"
#echo
#cp "${GIT_ROOT}/tools/pre-push" "${GIT_DIR}/hooks/pre-push" \
#  && chmod +x "${GIT_DIR}/hooks/pre-push"

echo "Installing git pre-commit hook"
echo
cp "${GIT_ROOT}/tools/pre-commit" "${GIT_DIR}/hooks/pre-commit" \
  && chmod +x "${GIT_DIR}/hooks/pre-commit"

cat <<-EOF
Please import the code style settings in Android Studio:
  * open Settings -> Editor -> Code Style
  * click the gear icon and select "Import Scheme..."
  * find the file ${GIT_ROOT}/tools/loteriamexicana-codestyle.xml

Additionally, checking the following settings helps avoid miscellaneous issues:
  * Settings -> Editor -> General -> Remove trailing spaces on: Modified lines
  * Settings -> Editor -> General -> Ensure every file ends with a line break
  * Settings -> Editor -> General -> Auto Import -> Optimize imports on the fly (for both Kotlin\
 and Java)
EOF

read -p "Press enter to continue"