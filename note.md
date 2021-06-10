k get pod | grep blog- | awk '{print $1}' | xargs k delete pod











