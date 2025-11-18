<script setup lang="ts">
import moment, { fn } from "moment";
import axios from "axios";
import DarkCom from "./components/DarkCom.vue";
import { MoreHorizontal, PlusCircle } from "lucide-vue-next";

import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Tabs, TabsContent } from "@/components/ui/tabs";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import {
  DateFormatter,
  type DateValue,
  getLocalTimeZone,
} from "@internationalized/date";
import { Calendar as CalendarIcon } from "lucide-vue-next";
import { Calendar } from "@/components/ui/calendar";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";
import { cn } from "./lib/utils";
import { onMounted, ref } from "vue";

let dataTable:any = ref(null);
let data = ref(null);
let date: string;
let date2: string;
// 添加车辆信息
let brandInput = "";
let typeInput = "";
let priceInput = "";
let colorInput = "";
// 修改车辆信息
let id2 = "";
let brandInput2 = "";
let typeInput2 = "";
let priceInput2 = "";
let colorInput2 = "";
let dataPurchaseInput2 = "";

// 添加日期表单
const df = new DateFormatter("en-US", {
  dateStyle: "long",
});

const dateInput = ref<DateValue>();

// 修改日期信息
const df2 = new DateFormatter("en-US", {
  dateStyle: "long",
});

const dateInput2 = ref<DateValue>();

function addItem() {
  console.log("dateInput:" + dateInput.value);
  axios
    .post("http://127.0.0.1:8080/vehicle", {
      brand: brandInput,
      type: typeInput,
      price: priceInput,
      color: colorInput,
      dataPurchase: String(dateInput.value),
    })
    .then((result) => {
      console.log(result.data);
      window.location.reload();
    })
    .catch((err) => {
      console.log(err);
    });
}
function getByIndex(index) {
  console.log(index);
  console.log(dataTable.value[index].brand);
  brandInput2 = dataTable.value[index].brand;
  typeInput2 = dataTable.value[index].type;
  priceInput2 = dataTable.value[index].price;
  colorInput2 = dataTable.value[index].color;
  dataPurchaseInput2 = dataTable.value[index].dataPurchase;
  console.log(String(dataTable.value[index].dataPurchase));
}
function getByid(id) {
  console.log(id);
  axios
    .get("http://127.0.0.1:8080/vehicle/" + id)
    .then((result) => {
      console.log(result);
      data = result.data.data;
      console.log("data:" + data);
      // data.value.forEach((item) => {
      //   date2 = moment(item.dataPurchase).format("YYYY-MM-DD");
      // });
      // brandInput2 = result.data.data.brand;
      // typeInput2 = result.data.data.type;
      // priceInput2 = result.data.data.price;
      // colorInput2 = result.data.data.color;
      // dataPurchaseInput2 = result.data.data.dataPurchase;
    })
    .catch((err) => {
      console.log(err);
    });
}
function updateItem(id) {
  console.log(id);
  console.log(
    brandInput2,
    typeInput2,
    priceInput2,
    colorInput2,
    dataPurchaseInput2
  );
  axios
    .put("http://127.0.0.1:8080/vehicle", {
      id: id,
      brand: brandInput2,
      type: typeInput2,
      price: priceInput2,
      color: colorInput2,
      dataPurchase: dataPurchaseInput2,
    })
    .then((result) => {
      console.log(result);
      window.location.reload();
    })
    .catch((err) => {
      console.log(err);
    });
}
function deleteItem(id) {
  axios
    .delete("http://127.0.0.1:8080/vehicle/" + id)
    .then((result) => {
      console.log(result.data);
      window.location.reload();
    })
    .catch((err) => {
      console.log(err);
    });
}
function example() {
  console.log("点击修改按钮");
}
onMounted(() => {
  axios
    .get("http://127.0.0.1:8080/vehicle")
    .then((result) => {
      dataTable.value = result.data.data;
      console.log("dataTable:" + dataTable.value);
      dataTable.value.forEach((item) => {
        date = moment(item.dataPurchase).format("YYYY-MM-DD");
      });
    })
    .catch((err) => {
      console.log("err:" + err);
    });
});
</script>

<template>
  <div class="flex min-h-screen w-full flex-col bg-muted/40">
    <div class="flex flex-col sm:gap-4 sm:py-4 sm:pl-14">
      <main class="grid flex-1 items-start gap-4 p-4 sm:px-6 sm:py-0 md:gap-8">
        <Tabs default-value="all">
          <div class="flex items-center">
            <div class="ml-auto flex items-center gap-2">
              <Dialog>
                <DialogTrigger>
                  <Button size="sm" class="h-7 gap-1">
                    <PlusCircle class="h-3.5 w-3.5" />
                    <span class="sr-only sm:not-sr-only sm:whitespace-nowrap">
                      添加
                    </span>
                  </Button>
                </DialogTrigger>
                <DialogContent>
                  <DialogHeader>
                    <DialogTitle>添加车辆信息</DialogTitle>
                    <DialogDescription></DialogDescription>
                  </DialogHeader>
                  <div class="grid gap-4 py-4">
                    <div class="grid grid-cols-4 items-center gap-4">
                      <Label for="brand" class="text-right"> 品牌 </Label>
                      <Input
                        id="brand"
                        v-model="brandInput"
                        class="col-span-3"
                      />
                    </div>
                    <div class="grid grid-cols-4 items-center gap-4">
                      <Label for="type" class="text-right"> 型号 </Label>
                      <Input id="type" v-model="typeInput" class="col-span-3" />
                    </div>
                    <div class="grid grid-cols-4 items-center gap-4">
                      <Label for="price" class="text-right"> 价格 </Label>
                      <Input
                        id="price"
                        v-model="priceInput"
                        class="col-span-3"
                      />
                    </div>
                    <div class="grid grid-cols-4 items-center gap-4">
                      <Label for="color" class="text-right"> 颜色 </Label>
                      <Input
                        id="color"
                        v-model="colorInput"
                        class="col-span-3"
                      />
                    </div>
                    <div class="grid grid-cols-4 items-center gap-4">
                      <Label for="date" class="text-right"> 生产日期 </Label>
                      <!-- <Input id="date" v-model="dateInput" class="col-span-3" /> -->
                      <Popover>
                        <PopoverTrigger as-child>
                          <Button
                            variant="outline"
                            :class="
                              cn(
                                'w-[280px] justify-start text-left font-normal',
                                !dateInput && 'text-muted-foreground'
                              )
                            "
                          >
                            <CalendarIcon class="mr-2 h-4 w-4" />
                            {{
                              dateInput
                                ? df.format(
                                    dateInput.toDate(getLocalTimeZone())
                                  )
                                : "购买日期"
                            }}
                          </Button>
                        </PopoverTrigger>
                        <PopoverContent class="w-auto p-0">
                          <Calendar v-model="dateInput" initial-focus />
                        </PopoverContent>
                      </Popover>
                    </div>
                  </div>
                  <DialogFooter>
                    <Button type="submit" @click="addItem()"> 提交 </Button>
                  </DialogFooter>
                </DialogContent>
              </Dialog>
              <DarkCom />
            </div>
          </div>
          <TabsContent value="all">
            <Card>
              <CardHeader>
                <CardTitle>Vehicle</CardTitle>
                <CardDescription> 基于ssm的汽车信息管理系统 </CardDescription>
              </CardHeader>
              <CardContent>
                <Table>
                  <TableHeader>
                    <TableRow>
                      <TableHead class="hidden w-[100px] sm:table-cell">
                        序号
                      </TableHead>
                      <TableHead>品牌</TableHead>
                      <TableHead>型号</TableHead>
                      <TableHead class="hidden md:table-cell"> 价格 </TableHead>
                      <TableHead class="hidden md:table-cell"> 颜色 </TableHead>
                      <TableHead class="hidden md:table-cell">
                        生产日期
                      </TableHead>
                      <TableHead> 操作 </TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    <TableRow v-for="(item, index) in dataTable" :key="index">
                      <TableCell class="hidden sm:table-cell">
                        {{ index + 1 }}
                      </TableCell>
                      <TableCell class="font-medium">
                        {{ item.brand }}
                      </TableCell>
                      <TableCell>
                        {{ item.type }}
                      </TableCell>
                      <TableCell class="hidden md:table-cell">
                        ￥{{ item.price }}
                      </TableCell>
                      <TableCell class="hidden md:table-cell">
                        {{ item.color }}
                      </TableCell>
                      <TableCell class="hidden md:table-cell">
                        <!-- item.dataPurchase -->
                        {{ item.dataPurchase }}
                      </TableCell>
                      <TableCell>
                        <Dialog>
                          <DialogTrigger>
                            <Button
                              class="buttonUpdate"
                              @click="getByIndex(index)"
                              >修改</Button
                            >
                          </DialogTrigger>
                          <DialogContent>
                            <DialogHeader>
                              <DialogTitle>修改车辆信息</DialogTitle>
                              <DialogDescription></DialogDescription>
                            </DialogHeader>
                            <div class="grid gap-4 py-4">
                              <div class="grid grid-cols-4 items-center gap-4">
                                <Label for="brand" class="text-right">
                                  品牌
                                </Label>
                                <Input
                                  id="brand"
                                  v-model="brandInput2"
                                  class="col-span-3"
                                />
                              </div>
                              <div class="grid grid-cols-4 items-center gap-4">
                                <Label for="type" class="text-right">
                                  型号
                                </Label>
                                <Input
                                  id="type"
                                  v-model="typeInput2"
                                  class="col-span-3"
                                />
                              </div>
                              <div class="grid grid-cols-4 items-center gap-4">
                                <Label for="price" class="text-right">
                                  价格
                                </Label>
                                <Input
                                  id="price"
                                  v-model="priceInput2"
                                  class="col-span-3"
                                />
                              </div>
                              <div class="grid grid-cols-4 items-center gap-4">
                                <Label for="color" class="text-right">
                                  颜色
                                </Label>
                                <Input
                                  id="color"
                                  v-model="colorInput2"
                                  class="col-span-3"
                                />
                              </div>
                              <div class="grid grid-cols-4 items-center gap-4">
                                <Label for="date" class="text-right">
                                  生产日期
                                </Label>
                                <!-- <Input id="date" v-model="dateInput" class="col-span-3" /> -->
                                <Popover>
                                  <PopoverTrigger as-child>
                                    <Button
                                      variant="outline"
                                      :class="
                                        cn(
                                          'w-[280px] justify-start text-left font-normal',
                                          !dateInput2 && 'text-muted-foreground'
                                        )
                                      "
                                    >
                                      <CalendarIcon class="mr-2 h-4 w-4" />
                                      {{
                                        dateInput2
                                          ? df2.format(
                                              dateInput2.toDate(
                                                getLocalTimeZone()
                                              )
                                            )
                                          : dataPurchaseInput2
                                      }}
                                    </Button>
                                  </PopoverTrigger>
                                  <PopoverContent class="w-auto p-0">
                                    <Calendar
                                      v-model="dateInput2"
                                      initial-focus
                                    />
                                  </PopoverContent>
                                </Popover>
                              </div>
                            </div>
                            <DialogFooter>
                              <Button
                                type="submit"
                                @click="updateItem(item.id)"
                              >
                                提交
                              </Button>
                            </DialogFooter>
                          </DialogContent>
                        </Dialog>

                        <Button @click="deleteItem(item.id)">删除</Button>
                      </TableCell>
                    </TableRow>
                  </TableBody>
                </Table>
              </CardContent>
              <CardFooter>
                <div class="text-xs text-muted-foreground">
                  完成于2024-06-21 02:03
                </div>
              </CardFooter>
            </Card>
          </TabsContent>
        </Tabs>
      </main>
    </div>
  </div>
</template>
<style>
.buttonUpdate {
  margin-right: 10px;
}
</style>
