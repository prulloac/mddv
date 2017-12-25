#/usr/bin/env bash

function __help {
  printf "\n- You must pass an argument with -n or --name in order to give the component a name\n"
}

NAME=
while :; do
  case $1 in
    -h|--help|-\?)
      __help
      ;;
    -n|--name)
      NAME=$2
      shift
      ;;
    \?)
      echo "Invalid option: -$1"
      __help
      exit 1
      ;;
    *)
      break
  esac
  shift
done

if [ ${NAME} ]; then
  echo "Component name: ${NAME}"
else
  __help
  printf "\nNo name provided. Exiting\n"
  exit 1
fi

mkdir -p src/components/$NAME 2>/dev/null
printf "import React, { Component } from 'react'\n\nimport './${NAME}.scss'\n\nclass ${NAME} extends Component {\n  render(){\n    return (null)\n  }\n}\n\nexport default ${NAME}\n" > src/components/${NAME}/${NAME}.js
touch src/components/${NAME}/${NAME}.scss
exit 0;
