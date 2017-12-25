#/usr/bin/env bash

function __help {
  printf "\n- You must pass an argument with -n or --name in order to give the container a name\n"
  printf "\n- Add -c or --component in order to create container with corresponding component\n"
}

COMPONENT=0
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
    -c|--component)
      COMPONENT=1
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
  echo "Container name: ${NAME}"
else
  __help
  printf "\nNo name provided. Exiting\n"
  exit 1
fi

if [ ${COMPONENT} -eq "1" ]; then
  ./new_component.sh --name ${NAME}
fi

mkdir -p src/containers/${NAME} 2>/dev/null
printf "import React, { Component } from 'react'\n\nimport ${NAME} from 'components/${NAME}'\n\nclass ${NAME}Container extends Component {\n  constructor(props){\n    super(props)\n    this.state = {\n\n    }\n  }\n\n  render(){\n    return (null)\n  }\n}\n\nexport default ${NAME}Container\n" > src/containers/${NAME}/${NAME}.js
exit 0;
