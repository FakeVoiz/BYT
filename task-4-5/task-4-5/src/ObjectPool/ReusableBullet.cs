using System;
using System.Threading;

namespace task_4_5.ObjectPool
{
    public class ReusableBullet
    {
        private static int _availableId = 1;
        private int _id;
        private Pool<ReusableBullet> _nativePool;
        
        
        public ReusableBullet(Pool<ReusableBullet> nativePool)
        {
            _id = _availableId;
            _availableId++;

            _nativePool = nativePool;
            Console.WriteLine($"Bullet#{_id} was created");
        }

        public void Launch()
        {
            Console.WriteLine($"Bullet#{_id} was launched");
        }

        public void Reuse()
        {
            _nativePool.AddReusableObject(this);
            
            Console.WriteLine($"Bullet#{_id} was reused");
        }
    }
}