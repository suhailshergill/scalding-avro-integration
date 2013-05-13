#!/bin/bash

DIR=$(cd $(dirname "${BASH_SOURCE[0]}") && pwd)
AVRO_RESOURCES="$DIR/../first-party/src/main/resources"
AVRO_SOURCE="$DIR/../first-party/src/main/java"
AVRO_TOOLS=$1

declare -a schemas=()
declare -a protocols=()
declare -a idls=()

shift

for f in "$@"
do
  case ${f#*.} in
    'avsc')
      schemas=(${schemas[@]} ${f%%.*})
      ;;
    'avpr')
      protocols=(${protocols[@]} ${f%%.*})
      ;;
    'avdl')
      idls=(${idls[@]} ${f%%.*})
      ;;
    *)
      echo "Unknown file extension: '${f#*.}'. Ignoring '$f'."
      ;;
  esac
done

compile_schema() {
  java -jar $AVRO_TOOLS compile -string schema "$AVRO_RESOURCES/$1.avsc" $AVRO_SOURCE
}

compile_protocol() {
  java -jar $AVRO_TOOLS compile -string protocol "$AVRO_RESOURCES/$1.avpr" $AVRO_SOURCE
}

compile_idl() {
  java -jar $AVRO_TOOLS idl "$AVRO_RESOURCES/$1.avdl" "$AVRO_RESOURCES/$1.avpr"
  compile_protocol $1
}

for schema in ${schemas[@]}
do
  compile_schema $schema
done

for protocol in ${protocols[@]}
do
  compile_protocol $protocol
done

for idl in ${idls[@]}
do
  compile_idl $idl
done
