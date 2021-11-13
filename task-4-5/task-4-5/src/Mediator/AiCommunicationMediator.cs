using System;

namespace task_4_5.Mediator
{
    public class AiCommunicationMediator : Mediator
    {
        private AiCommunicationComponent _aiCommunicationComponent1;
        private AiCommunicationComponent _aiCommunicationComponent2;

        public AiCommunicationMediator(AiCommunicationComponent aiCommunicationComponent1, AiCommunicationComponent aiCommunicationComponent2)
        {
            _aiCommunicationComponent1 = aiCommunicationComponent1;
            _aiCommunicationComponent2 = aiCommunicationComponent2;

            _aiCommunicationComponent1.Mediator = this;
            _aiCommunicationComponent2.Mediator = this;
        }

        public void PassMessage(MediatorComponent mediatorComponent, MessageType messageType)
        {
            var aiToNotify = mediatorComponent == _aiCommunicationComponent1
                ? _aiCommunicationComponent2
                : _aiCommunicationComponent1;
            
            Console.WriteLine($"AI#{aiToNotify.Id} receives message {messageType}");
            
            switch (messageType)
            {
                case MessageType.EnemySpotted:
                    aiToNotify.AttackEnemy();
                    break;
                case MessageType.EnemyLost:
                    aiToNotify.SearchForEnemy();
                    break;
            }
        }
    }
}