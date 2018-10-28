#!/usr/bin/env bash


function createImage {
    pushd worker/
    docker build . -t open-nebula-worker-node
    popd
}

createImage