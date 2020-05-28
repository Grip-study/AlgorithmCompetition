import Foundation

func solution(_ tickets:[[String]]) -> [String] {
    let tickets = tickets.sorted {
        if $0.first! == $1.first! {
            return $0.last! < $1.last!
        }
        return $0.first! < $1.first!
    }

    var length = tickets.count + 1
    var totalRoutes = [[String]]()

    var passedTickets = [Int: String]()
    var route = [String]()

    func searchNext(_ value: String, currentIndex: Int) {
        for (index, ticket) in tickets.enumerated() {
            var verifySameWithPrior = true
            if route.count > 0, let last = totalRoutes.last?[0...route.count].map({ $0 }) {
                verifySameWithPrior = last != route + [ticket.first]
            }

            if passedTickets[index] == nil, ticket.first == value, verifySameWithPrior {
                route.append(ticket.first!)
                passedTickets.updateValue(ticket.first!, forKey: index)

                if route.count == length - 1 {
                    totalRoutes.append(route + [ticket.last!])

                    route.removeLast(2)
                    passedTickets.removeValue(forKey: index)
                    passedTickets.removeValue(forKey: currentIndex)
                    return
                }

                searchNext(ticket.last!, currentIndex: index)
            }
        }

        if route.count > 0 {
            route.removeLast()
            passedTickets.removeValue(forKey: currentIndex)
        }
    }

   func getResult() -> [String] {
        guard totalRoutes.count > 1 else { return totalRoutes.first ?? [] }

        let merged = totalRoutes.enumerated().map { index, route -> (Int, String) in
            let arranged = route.reduce("") { result, next in
                return result + String(next.first!)
            }
            return (index, arranged)
        }

        let minIndex = merged.min { $0.1 < $1.1 }?.0 ?? 0
        return totalRoutes[minIndex]
    }

    searchNext("ICN", currentIndex: tickets.firstIndex { $0.first == "ICN" }!)

    return getResult()
}

