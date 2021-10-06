export class MyBasketDataProvider {
    constructor() {
        this.products = [
            { id: "1", name: 'Computer', price: 1722.44, quantity: 1 },
            { id: "2", name: 'Phone', price: 522.14, quantity: 1 }
        ];
        this.items = [
            { id: "1", name: 'Computer', price: 1722.44, quantity: 1 },
            { id: "2", name: 'Phone', price: 522.14, quantity: 2 }
        ];
        this.getInitialData = () => {
            return new Promise((resolve, reject) => {
                setTimeout(() => {
                    resolve(this.items);
                }, 1000);
            });
        };
        this.onItemDeleted = (id) => {
            return new Promise((resolve, reject) => {
                setTimeout(() => {
                    const index = this.items.findIndex(item => item.id === id);
                    if (index > -1) {
                        this.items.splice(index, 1);
                    }
                    resolve(this.items);
                }, 1000);
            });
        };
    }
    registerToChanges(callback) {
        // You can call callback functions if you socket.io/pusher or something like it.  
    }
    onAllItemsDeleted() {
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                this.items = [];
                resolve(this.items);
            }, 1000);
        });
    }
    onItemAdded(id) {
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                let index = this.items.findIndex(item => item.id === id);
                if (index > -1) {
                    this.items[index].quantity++;
                }
                else {
                    const index = this.products.findIndex(item => item.id === id);
                    if (index > -1) {
                        const item = Object.assign({}, this.products[index]);
                        this.items.push(item);
                    }
                }
                resolve(this.items);
            }, 1000);
        });
    }
}
