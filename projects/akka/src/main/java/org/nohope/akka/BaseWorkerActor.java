package org.nohope.akka;

import static org.nohope.akka.SupervisorRequests.StartupReply;
import static org.nohope.akka.SupervisorRequests.StartupRequest;

/**
 * Date: 9/21/12
 * Time: 10:53 AM
 */
public abstract class BaseWorkerActor extends MessageTypeMatchingActor {
    protected final NamedWorkerMetadata workerMetadata;

    protected BaseWorkerActor(final NamedWorkerMetadata workerMetadata,
                              final boolean expandObjectArrays) {
        super(expandObjectArrays);
        this.workerMetadata = workerMetadata;
    }

    protected BaseWorkerActor(final NamedWorkerMetadata workerMetadata) {
        this(workerMetadata, false);
    }

    public NamedWorkerMetadata getWorkerMetadata() {
        return workerMetadata;
    }

    @OnReceive
    private StartupReply processStartupRequest(final StartupRequest request) {
        log.debug("Sending startup notification to supervisor");
        return new StartupReply(workerMetadata);
    }
}
