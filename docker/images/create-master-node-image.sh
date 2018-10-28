#!/usr/bin/env bash


function createImage {
    pushd master/
    docker build . -t open-nebula-master-node
    popd
}

createImage