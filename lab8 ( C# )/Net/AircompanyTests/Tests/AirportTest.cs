using Aircompany;
using Aircompany.Models;
using Aircompany.Planes;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using Newtonsoft.Json;
using Newtonsoft.Json;

namespace AircompanyTests.Tests
{
    [TestFixture]
    public class AirportTest
    {
        private List<Plane> planes = new List<Plane>();
           

        private PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);

        [Test]
        public void MyTest1()
        {
            Airport airport = new Airport(planes);
            List<MilitaryPlane> transportMilitaryPlanes = airport.GetTransportMilitaryPlanes().ToList();
            bool hasMilitaryTransportPlane = false;
            foreach (MilitaryPlane militaryPlane in transportMilitaryPlanes)
            {
                if ((militaryPlane.PlaneTypeIs() == MilitaryType.TRANSPORT))
                {
                    hasMilitaryTransportPlane = true;
                }
            }
            Assert.IsTrue(hasMilitaryTransportPlane);
        }

        [Test]
        public void MyTest2()
        {
            Airport airport = new Airport(planes);
            PassengerPlane expectedPlaneWithMaxPassengersCapacity = airport.GetPassengerPlaneWithMaxPassengersCapacity();           
        }

        [Test]
        public void MyTest3()
        {
            Airport airport = new Airport(planes);
            airport = airport.SortByMaxLoadCapacity();
            List<Plane> planesSortedByMaxLoadCapacity = airport.GetPlanes().ToList();

            bool nextPlaneMaxLoadCapacityIsHigherThanCurrent = true;
            for (int i = 0; i < planesSortedByMaxLoadCapacity.Count - 1; i++)
            {
                Plane currentPlane = planesSortedByMaxLoadCapacity[i];
                Plane nextPlane = planesSortedByMaxLoadCapacity[i + 1];
                if (currentPlane.MAXLoadCapacity() > nextPlane.MAXLoadCapacity())
                {
                    nextPlaneMaxLoadCapacityIsHigherThanCurrent = false;
                }
            }
            Assert.That(nextPlaneMaxLoadCapacityIsHigherThanCurrent==true);
        }
    }
}
