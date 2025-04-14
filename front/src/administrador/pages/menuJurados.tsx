import Header from "../components/Header";
import returnIcon from "../assets/return.png";
import { motion } from "framer-motion";
import { fadeUpTitle } from "../../core/animations/cardVariants";
import {
  Table,
  TableBody,
  TableHeader,
  TableColumn,
  TableRow,
  TableCell,
} from "@heroui/table";

export default function MenuJurados() {
  return (
    <div className="min-h-screen bg-[#2b1e49]">
      <Header />

      <div className="flex flex-col items-center py-6 px-5">
        <div className="w-full max-w-4xl">
          <motion.div className="flex items-center gap-3 mb-6" {...fadeUpTitle}>
            <img
              src={returnIcon}
              alt="Voltar"
              className="w-8 h-8 cursor-pointer"
              onClick={() => window.history.back()}
            />
            <h1 className="text-3xl font-bold text-[#fee9c9] font-title">
              Gerenciamento de Jurados
            </h1>
          </motion.div>

          <div className="bg-white rounded-2xl shadow-lg p-4">
            <Table>
              <TableHeader>
                <TableRow>
                  <TableColumn className="text-left text-sm text-gray-700 font-semibold">
                    Nome
                  </TableColumn>
                  <TableColumn className="text-left text-sm text-gray-700 font-semibold">
                    Especialidade
                  </TableColumn>
                  <TableColumn className="text-left text-sm text-gray-700 font-semibold">
                    Status
                  </TableColumn>
                  <TableColumn className="text-left text-sm text-gray-700 font-semibold">
                    Ações
                  </TableColumn>
                </TableRow>
              </TableHeader>

              <TableBody>
                {/* Exemplo de jurado */}
                <TableRow>
                  <TableCell>Lucas Silva</TableCell>
                  <TableCell>Culinária Brasileira</TableCell>
                  <TableCell>
                    <span className="text-green-500 font-bold">Ativo</span>
                  </TableCell>
                  <TableCell>
                    <button className="text-blue-600 hover:underline">
                      Editar
                    </button>
                    <button className="ml-4 text-red-600 hover:underline">
                      Remover
                    </button>
                  </TableCell>
                </TableRow>

                {/* Outra linha de jurado exemplo */}
                <TableRow>
                  <TableCell>Maria Oliveira</TableCell>
                  <TableCell>Culinária Italiana</TableCell>
                  <TableCell>
                    <span className="text-red-500 font-bold">Inativo</span>
                  </TableCell>
                  <TableCell>
                    <button className="text-blue-600 hover:underline">
                      Editar
                    </button>
                    <button className="ml-4 text-red-600 hover:underline">
                      Remover
                    </button>
                  </TableCell>
                </TableRow>

                {/* Caso não tenha jurados */}
                <TableRow>
                  <TableCell colSpan={4} className="text-center text-gray-500 py-6">
                    Nenhum jurado cadastrado ainda.
                  </TableCell>
                </TableRow>
              </TableBody>
            </Table>
          </div>
        </div>
      </div>
    </div>
  );
}
