package com.mslfox.oniipswingjpamvc.view;

public interface Command<U> {
     Object execute(U u);
}
