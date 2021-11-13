using System;

namespace task_4_5.Mediator
{
    public class AiCommunicationComponent : MediatorComponent
    {
        private static int _availableId = 1;
        public int Id { get; private set; }

        public AiCommunicationComponent(Mediator mediator = null) : base(mediator)
        {
            Id = _availableId;
            _availableId++;
        }

        public void SpotEnemy()
        {
            Console.WriteLine($"AI#{Id} tells that enemy was spotted");
            Mediator.PassMessage(this, MessageType.EnemySpotted);
        }

        public void LooseEnemy()
        {
            Console.WriteLine($"AI#{Id} tells that enemy was lost");
            Mediator.PassMessage(this, MessageType.EnemyLost);
        }

        public void SearchForEnemy()
        {
            Console.WriteLine($"AI#{Id} is searching for enemy");
        }

        public void AttackEnemy()
        {
            Console.WriteLine($"AI#{Id} is attacking enemy");
        }
    }
}