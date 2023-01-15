using Aircompany.Models;
using Aircompany.Planes;
using System.Collections.Generic;
using System.Linq;

namespace Aircompany
{
    public class Airport
    {
        public List<Plane> _planes;

        public Airport(IEnumerable<Plane> planes)
        {
            _planes = planes.ToList();
        }

        public List<PassengerPlane> GetPassengerPlanes()
        {
           var passengerPlanes = new List<PassengerPlane>();
           foreach (var plane in _planes)
                if (plane.GetType() == typeof(PassengerPlane))
                    passengerPlanes.Add((PassengerPlane)plane);
            return passengerPlanes;
        }

        public PassengerPlane GetPassengerPlaneWithMaxPassengersCapacity() =>
            GetPassengerPlanes().Aggregate((m, c) => m.PassengersCapacity > c.PassengersCapacity ? m : c);

        public List<MilitaryPlane> GetMilitaryPlanes()
        {
           var militaryPlanes = new List<MilitaryPlane>();
           foreach(var plane in _planes)
                if (plane.GetType() == typeof(MilitaryPlane))
                    militaryPlanes.Add((MilitaryPlane)plane);
            return militaryPlanes;
        }

        public List<MilitaryPlane> GetMilitaryPlanesByType(MilitaryType militaryPlaneType)
        {
            var militaryPlanesByType = new List<MilitaryPlane>();
            var militaryPlanes = GetMilitaryPlanes();

            foreach (var plane in militaryPlanes)
                if (plane.Type == militaryPlaneType)
                    militaryPlanesByType.Add(plane);

            return militaryPlanesByType;
        }

        public Airport SortByMaxDistance() => new Airport(_planes.OrderBy(p => p.MaxFlightDistance));

        public Airport SortByMaxSpeed() => new Airport(_planes.OrderBy(p => p.MaxSpeed));

        public Airport SortByMaxLoadCapacity() => new Airport(_planes.OrderBy(p => p.MaxLoadCapacity));


        public override string ToString() => $"Airport: planes = {string.Join(", ", _planes.Select(x => x.Model))}\n";
    }
}
