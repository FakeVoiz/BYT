namespace task_4_5.Mediator
{
    public interface Mediator
    {
        public void PassMessage(MediatorComponent mediatorComponent, MessageType messageType);
    }
}